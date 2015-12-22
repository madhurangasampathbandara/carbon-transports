/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package org.wso2.carbon.transport.http.netty.sender.channel.pool;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.wso2.carbon.transport.http.netty.common.Constants;

import java.util.Map;

/**
 * A class which represents connection pool specific parameters.
 */
public class PoolConfiguration {


    private static PoolConfiguration poolConfiguration;

    private int maxActivePerPool = -1;

    private int minIdlePerPool;

    private int maxIdlePerPool = 100;

    private boolean testOnBorrow = true;

    private boolean testWhileIdle = true;

    private long timeBetweenEvictionRuns = 30 * 1000L;

    private long minEvictableIdleTime = 5 * 60 * 1000L;

    private byte exhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_GROW;

    private int numberOfPools = 0;

    private int executorServiceThreads = 20;


    private PoolConfiguration(Map<String, String> parameters) {

        if (parameters != null) {
            numberOfPools = parameters.get(Constants.NUMBER_OF_POOLS) != null ?
                            Integer.parseInt(parameters.get(Constants.NUMBER_OF_POOLS)) : 0;
            maxActivePerPool = parameters.get(Constants.MAX_ACTIVE_CONNECTIONS_PER_POOL) != null ?
                               Integer.parseInt(parameters.get(Constants.MAX_ACTIVE_CONNECTIONS_PER_POOL)) : -1;
            minIdlePerPool = parameters.get(Constants.MIN_IDLE_CONNECTIONS_PER_POOL) != null ?
                             Integer.parseInt
                                        (parameters.get(Constants.MIN_IDLE_CONNECTIONS_PER_POOL)) : 0;
            maxIdlePerPool = parameters.get(Constants.MAX_IDLE_CONNECTIONS_PER_POOL) != null ?
                             Integer.parseInt(parameters.get(Constants.MAX_IDLE_CONNECTIONS_PER_POOL)) : 100;
            minEvictableIdleTime = parameters.get(Constants.MIN_EVICTION_IDLE_TIME) != null ?
                                   Integer.parseInt(parameters.get(Constants.MIN_EVICTION_IDLE_TIME)) : 5 * 60 * 1000L;
            executorServiceThreads = parameters.get(Constants.NO_THREADS_IN_EXECUTOR_SERVICE) != null ?
                                     Integer.parseInt(parameters.get(Constants.NO_THREADS_IN_EXECUTOR_SERVICE)) : 20;

        }

    }


    public static PoolConfiguration getInstance() {
        return poolConfiguration;

    }

    public static void createPoolConfiguration(Map<String, String> parameters) {
        poolConfiguration = new PoolConfiguration(parameters);
    }


    public int getMaxActivePerPool() {
        return maxActivePerPool;
    }

    public int getMinIdlePerPool() {
        return minIdlePerPool;
    }

    public int getMaxIdlePerPool() {
        return maxIdlePerPool;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public long getTimeBetweenEvictionRuns() {
        return timeBetweenEvictionRuns;
    }

    public long getMinEvictableIdleTime() {
        return minEvictableIdleTime;
    }

    public byte getExhaustedAction() {
        return exhaustedAction;
    }

    public int getNumberOfPools() {
        return numberOfPools;
    }

    public int getExecutorServiceThreads() {
        return executorServiceThreads;
    }
}
