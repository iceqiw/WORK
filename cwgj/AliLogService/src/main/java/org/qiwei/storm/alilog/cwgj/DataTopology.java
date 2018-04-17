package org.qiwei.storm.alilog.cwgj;

import static org.qiwei.storm.alilog.Constant.DATA_SPLIT_BOLT_ID;
import static org.qiwei.storm.alilog.Constant.LOG_SPOUT_ID;
import static org.qiwei.storm.alilog.Constant.PUSH_EXEC_BOLT_ID;
import static org.qiwei.storm.alilog.Constant.STAT_DAY_EXEC_BOLT_ID;
import static org.qiwei.storm.alilog.Constant.STAT_EXEC_BOLT_ID;
import static org.qiwei.storm.alilog.Constant.STREAM_ADV;
import static org.qiwei.storm.alilog.Constant.STREAM_DAY_STAT;
import static org.qiwei.storm.alilog.Constant.STREAM_STAT;
import static org.qiwei.storm.alilog.Constant.TOPOLOGY_NAME;

import com.aliyun.openservices.log.common.LogGroupData;
import com.aliyun.openservices.loghub.client.config.LogHubCursorPosition;
import com.aliyun.openservices.loghub.stormspout.LogGroupDataSerializSerializer;
import com.aliyun.openservices.loghub.stormspout.LogHubSpout;
import com.aliyun.openservices.loghub.stormspout.LogHubSpoutConfig;
import org.apache.log4j.Logger;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.qiwei.storm.alilog.LogConf;
import org.qiwei.storm.alilog.cwgj.adv.AdvSaveHbaseBolt;
import org.qiwei.storm.alilog.cwgj.stat.StatDayHbaseBolt;
import org.qiwei.storm.alilog.cwgj.stat.StatHbaseBolt;


public class DataTopology {

  private static final Logger logger = Logger.getLogger("DataTopology");


  public static void main(String[] args) {
    // 构建一个 Loghub Storm Spout 需要使用的配置
    LogHubSpoutConfig config = new LogHubSpoutConfig(LogConf.conumser_group_name,
        LogConf.endpoint, LogConf.project, LogConf.logstore, LogConf.access_id,
        LogConf.access_key, LogHubCursorPosition.END_CURSOR);
    TopologyBuilder builder = new TopologyBuilder();
    // 构建 loghub storm spout
    LogHubSpout spout = new LogHubSpout(config);
    // 在实际场景中，Spout的个数可以和Logstore Shard 个数相同
    builder.setSpout(LOG_SPOUT_ID, spout, 1);
    builder.setBolt(DATA_SPLIT_BOLT_ID, new DataSentenceBolt()).shuffleGrouping(LOG_SPOUT_ID);

    /**
     * 按日期统计
     */
    builder.setBolt(STAT_DAY_EXEC_BOLT_ID, new StatDayHbaseBolt())
        .fieldsGrouping(DATA_SPLIT_BOLT_ID, STREAM_DAY_STAT, new Fields("event"));
    /**
     * 统计
     */
    builder.setBolt(STAT_EXEC_BOLT_ID, new StatHbaseBolt())
        .fieldsGrouping(DATA_SPLIT_BOLT_ID, STREAM_STAT, new Fields("event"));
    /**
     * 广告
     */
    builder.setBolt(PUSH_EXEC_BOLT_ID, new AdvSaveHbaseBolt())
        .fieldsGrouping(DATA_SPLIT_BOLT_ID, STREAM_ADV, new Fields("event"));

    Config conf = new Config();
    conf.setMaxSpoutPending(1);
    // 如果使用Kryo进行数据的序列化和反序列化，则需要显示设置 LogGroupData 的序列化方法 LogGroupDataSerializSerializer
    Config.registerSerialization(conf, LogGroupData.class, LogGroupDataSerializSerializer.class);

    if (LogConf.mode.equals("Local")) {
      logger.info("Local mode...");
      conf.setDebug(false);
      LocalCluster cluster = new LocalCluster();
      cluster.submitTopology(TOPOLOGY_NAME, conf, builder.createTopology());

    } else if (LogConf.mode.equals("Remote")) {
      logger.info("Remote mode...");
      conf.setDebug(false);
      conf.setNumWorkers(8);
      try {
        StormSubmitter.submitTopology(TOPOLOGY_NAME, conf, builder.createTopology());
      } catch (InvalidTopologyException e) {
        e.printStackTrace();
      } catch (AlreadyAliveException e) {
        e.printStackTrace();
      } catch (AuthorizationException e) {
        e.printStackTrace();
      }
    } else {
      logger.error("invalid mode: " + LogConf.mode);
    }
  }
}
