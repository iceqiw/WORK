package org.qiwei.storm.alilog.cwgj.stat;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.qiwei.storm.alilog.sql.SQLcontext;
import org.qiwei.storm.alilog.utils.DateUtils;
import org.qiwei.storm.alilog.utils.PhoenixUtils;

public class StatDayHbaseBolt extends BaseRichBolt {

  private static final long serialVersionUID = 48526567770402264L;
  private static final Logger logger = Logger.getLogger("StatDayHbaseBolt");
  private OutputCollector mCollector;
  Map<String, Long> counts = new HashMap<>();

  @Override
  public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
    PhoenixUtils.start();
    this.mCollector = collector;
  }

  @Override
  public void execute(Tuple tuple) {
    String event = tuple.getStringByField("event");
    String curdt = tuple.getStringByField("curdt");
    String key = event + "::" + curdt;
    Long count = counts.get(key);
    if (count == null) {
      cleanYestoday(event);
      count = 0L;
    }
    count++;
    counts.put(key, count);
    logger.info(key + ":" + count);
    saveHbase(event, curdt, count.toString());
    mCollector.ack(tuple);
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    // do nothing
  }

  @Override
  public void cleanup() {
    System.out.println("--- FINAL COUNTS ---");
    PhoenixUtils.stop();
  }

  private void cleanYestoday(String event) {
    String key = event + "::" + DateUtils.getYestoday();
    if (counts.containsKey(key)) {
      counts.remove(key);
    }
  }

  private void saveHbase(String event, String date, String value) {
    try {
      PhoenixUtils.upset(SQLcontext.UPSET_STAT_DAY_DATA, event, date, value);
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }
}