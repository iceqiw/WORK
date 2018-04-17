package org.qiwei.storm.alilog.cwgj.adv;

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

public class AdvSaveHbaseBolt extends BaseRichBolt {

  private static final long serialVersionUID = 4852656887770402264L;
  private static final Logger logger = Logger.getLogger("AdvSaveHbaseBolt");
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
    if (event.equalsIgnoreCase("event_push_adv")) {
      String push_type = object.getString("push_type");
      String push_id = object.getString("push_id");
      String adv_id = object.getString("adv_id");
      String adv_name = object.getString("adv_name");
      String money = object.getString("money");
      String telephone = object.getString("telephone");
      String push_time = object.getString("push_time");
      try {
        PhoenixUtils.upset(SQLcontext.UPSET_SAVE_ADV, push_id, push_type, adv_id, adv_name, money,
            telephone, push_time);
      } catch (SQLException e) {
        logger.error(e.getMessage());
      }
    }
    if (event.equalsIgnoreCase("event_read_adv")) {
      String push_type = object.getString("push_type");
      String push_id = object.getString("push_id");
      String adv_id = object.getString("adv_id");
      String read_time = object.getString("read_time");
      try {
        PhoenixUtils.upset(SQLcontext.UPSET_UPDATA_ADV, push_id, push_type, adv_id, read_time);
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