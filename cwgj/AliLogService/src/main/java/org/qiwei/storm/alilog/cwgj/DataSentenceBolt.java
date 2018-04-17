package org.qiwei.storm.alilog.cwgj;

import static org.qiwei.storm.alilog.Constant.STREAM_ADV;
import static org.qiwei.storm.alilog.Constant.STREAM_DAY_STAT;
import static org.qiwei.storm.alilog.Constant.STREAM_STAT;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.log.common.LogGroupData;
import com.aliyun.openservices.log.common.Logs;
import com.aliyun.openservices.loghub.stormspout.LogHubSpout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.qiwei.storm.alilog.utils.DateUtils;

public class DataSentenceBolt extends BaseRichBolt {

  private static final long serialVersionUID = 4752656887774402264L;
  private static final Logger logger = Logger.getLogger("DataSentenceBolt");
  private OutputCollector mCollector;


  @Override
  public void prepare(Map stormConf, TopologyContext context,
      OutputCollector collector) {
    this.mCollector = collector;
  }

  @Override
  public void execute(Tuple tuple) {
    logger.info("running.....");
    List<LogGroupData> logGroupDatas = (ArrayList<LogGroupData>) tuple
        .getValueByField(LogHubSpout.FIELD_LOGGROUPS);
    for (LogGroupData groupData : logGroupDatas) {
      // 每个 LogGroup 由一条或多条日志组成
      Logs.LogGroup logGroup = groupData.GetLogGroup();
      for (Logs.Log log : logGroup.getLogsList()) {
        Logs.Log.Content content = log.getContents(0);
        JSONObject object = JSON.parseObject(content.getValue());

        /**
         * 广告
         */
        if ("event_push_adv".equalsIgnoreCase(object.getString("event"))) {
          this.mCollector.emit(STREAM_ADV, new Values("event_push_adv", object));
        }
        if ("event_read_adv".equalsIgnoreCase(object.getString("event"))) {
          this.mCollector.emit(STREAM_ADV, new Values("event_read_adv", object));
        }

        /**
         * 统计
         */
        if ("event_wx_register".equalsIgnoreCase(object.getString("event"))) {
          this.mCollector.emit(STREAM_STAT, new Values("event_register", object));
        }
        if ("event_app_register".equalsIgnoreCase(object.getString("event"))) {
          this.mCollector.emit(STREAM_STAT, new Values("event_register", object));
        }
        if ("event_park_all_order".equalsIgnoreCase(object.getString("event"))) {
          this.mCollector.emit(STREAM_STAT, new Values("event_park_all_order", object));
        }

        /**
         * 按日期统计
         */
        if ("event_wechat_out_push".equalsIgnoreCase(object.getString("event"))) {
          String curdt = DateUtils.getToday();
          this.mCollector.emit(STREAM_DAY_STAT, new Values("event_push_wx", curdt, object));
        }
        if ("event_pay_success_push".equalsIgnoreCase(object.getString("event"))) {
          String curdt = DateUtils.getToday();
          this.mCollector.emit(STREAM_DAY_STAT, new Values("event_push_wx", curdt, object));
        }
        if ("event_advance_paypush".equalsIgnoreCase(object.getString("event"))) {
          String curdt = DateUtils.getToday();
          this.mCollector.emit(STREAM_DAY_STAT, new Values("event_push_wx", curdt, object));
        }
        if ("event_wechat_in_push".equalsIgnoreCase(object.getString("event"))) {
          String curdt = DateUtils.getToday();
          this.mCollector.emit(STREAM_DAY_STAT, new Values("event_push_wx", curdt, object));
        }
        if ("event_rent_expires_push".equalsIgnoreCase(object.getString("event"))) {
          String curdt = DateUtils.getToday();
          this.mCollector.emit(STREAM_DAY_STAT, new Values("event_push_wx", curdt, object));
        }
        if ("event_pay_monthly_success".equalsIgnoreCase(object.getString("event"))) {
          String curdt = DateUtils.getToday();
          this.mCollector.emit(STREAM_DAY_STAT, new Values("event_push_wx", curdt, object));
        }


      }
    }
    mCollector.ack(tuple);
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declareStream(STREAM_ADV, new Fields("event", "jsonmsg"));
    declarer.declareStream(STREAM_STAT, new Fields("event", "jsonmsg"));
    declarer.declareStream(STREAM_DAY_STAT, new Fields("event", "curdt", "jsonmsg"));
  }
}