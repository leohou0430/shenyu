/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.agent.plugin.metrics.prometheus.recorder;

import io.prometheus.client.Counter;
import org.apache.shenyu.agent.plugin.metrics.api.MetricsRecorder;
import org.apache.shenyu.agent.api.config.MetricsConfig.Metric;

import java.util.Objects;

/**
 * The type Counter recorder.
 */
public final class CounterRecorder implements MetricsRecorder {
    
    private final Counter counter;
    
    /**
     * Instantiates a new Counter recorder.
     *
     * @param metric the metric
     */
    public CounterRecorder(final Metric metric) {
        Counter.Builder builder = Counter.build()
                .name(metric.getName())
                .help(metric.getHelp());
        if (Objects.nonNull(metric.getLabelNames()) && !metric.getLabelNames().isEmpty()) {
            builder.labelNames(metric.getLabelNames().toArray(new String[0]));
        }
        this.counter = builder.register();
    }
    
    @Override
    public void inc(final double value) {        
        counter.inc(value);
    }
    
    @Override
    public void inc(final double value, final String... labels) {
        counter.labels(labels).inc(value);
    }
}