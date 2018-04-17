package org.qiwei.storm.alilog.cwgj.stat;

import com.alibaba.fastjson.JSONObject;
import java.sql.SQLException;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.qiwei.storm.alilog.sql.SQLcontext;
import org.qiwei.storm.alilog.utils.PhoenixUtils;

public class StatHbaseBolt extends BaseRichBolt {

  private static final long serialVersionUID = 48526567770402264L;
  private static final Logger logger = Logger.getLogger("StatHbaseBolt");
  private OutputCollector mCollector;

  @Override
  public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
    PhoenixUtils.start();
    this.mCollector = collector;
  }

  @Override
  public void execute(Tuple tuple) {
    String event = tuple.getStringByField("event");
    JSONObject object = (JSONObject) tuple.getValueByField("jsonmsg");
    logger.info(object.toJSONString());
    if (event.equalsIgnoreCase("event_register")) {
      try {
        PhoenixUtils.upset(SQLcontext.UPSET_STAT_DATA_USER, "event_register");
      } catch (SQLException e) {
        logger.error(e.getMessage());
      }
    }

    if (event.equalsIgnoreCase("event_park_all_order")) {
      try {
        PhoenixUtils.upset(SQLcontext.UPSET_STAT_DATA_ORDER, "event_park_all_order");
      } catch (SQLException e) {
        logger.error(e.getMessage());
      }
    }
    mCollector.ack(tuple);
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    //do nothing
  }

  @Override
  public void cleanup() {
    System.out.println("--- FINAL COUNTS ---");
    PhoenixUtils.stop();
  }

}