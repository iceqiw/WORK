package org.qiwei.storm.alilog;

import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

public class CounterBolt extends BaseRichBolt {
    private static final long serialVersionUID = 4752656887772402264L;
    private static final Logger logger = Logger.getLogger("CounterBolt");
    private OutputCollector mCollector;
    private HashMap<String, Long> counts = null;

    @Override
    public void prepare(Map stormConf, TopologyContext context,
                        OutputCollector collector) {
        mCollector = collector;
        this.counts = new HashMap<>();
    }

    @Override
    public void execute(Tuple tuple) {
        String word = tuple.getStringByField("word");
        Long count = this.counts.get(word);
        if (count == null) {
            count = 0L;
        }
        count++;
        this.counts.put(word, count);
        this.mCollector.emit(new Values(word, count));
        mCollector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "count"));
    }
}