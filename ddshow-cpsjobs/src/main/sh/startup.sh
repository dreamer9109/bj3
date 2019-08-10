#!/bin/bash

# -------------------------------------------------------
# 方法
# -------------------------------------------------------

function REPORT
{
    [ $# -eq 2 ] && echo -e "\033[33m \033[1m [`date +%F\ %H:%M:%S`] $1 \033[0m" || echo -e "\033[1m [`date +%F\ %H:%M:%S`] $1 \033[0m"
    [ $# -eq 2 ] && exit 1 || return 0
}

#Extension of REPORT，报错 & 退出
function REPORTWARN
{
    REPORT "[ERROR] $1" 1
}

#Extension of REPORT，提示信息
function REPORTINFO
{
    REPORT "[INFO] $1"
}

# 检查参数
[ $# -ne 1 ] && REPORTWARN "Usage:[sh $0 PROFILE]"

# 配置
DEPLOY_TARGET_PATH=/opt/task
MODULE_NAME=ddshow-cpsjob
MODULE_VERSION=0.0.1-SNAPSHOT
LOG_PATH=${DEPLOY_TARGET_PATH}/logs
[ -d ${LOG_PATH} ] || mkdir -p ${LOG_PATH}
STARTUP_LOG=${LOG_PATH}/startup_${MODULE_NAME}.log
MODULE_TARGET_NAME=${MODULE_NAME}-${MODULE_VERSION}
PROFILE=$1
COMMAND_NAME=com.laifeng.cpsjobs.boot.Bootstrap
CUSTOM_PARAMS=
# JAVA_OPTS="${JAVA_OPTS} -server -Xms4g -Xmx4g  -XX:SurvivorRatio=4 -Xss256k -XX:PermSize=256m -XX:MaxPermSize=256m -XX:-DisableExplicitGC -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0 -XX:+CMSClassUnloadingEnabled -XX:-CMSParallelRemarkEnabled -XX:CMSInitiatingOccupancyFraction=70 -XX:ParallelCMSThreads=8 -XX:ParallelGCThreads=8 -XX:MaxTenuringThreshold=5 -XX:-UseAdaptiveSizePolicy -XX:TargetSurvivorRatio=90 -XX:+ScavengeBeforeFullGC -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOG_PATH}/startup_oom.log -Xloggc:${LOG_PATH}/startup_gc.log"

# 启动
# kill
PID=`ps -ef | grep -v grep | grep ${COMMAND_NAME} | grep ${DEPLOY_TARGET_PATH}/ | awk '{print $2}'`
[ "${PID}" != "" ] && kill -9 ${PID}
# start
nohup java $JAVA_OPTS -Dspring.profiles.active=${PROFILE} ${CUSTOM_PARAMS} -cp ${DEPLOY_TARGET_PATH}/${MODULE_TARGET_NAME}/classes:${DEPLOY_TARGET_PATH}/${MODULE_TARGET_NAME}/lib/* ${COMMAND_NAME} >> ${STARTUP_LOG} 2>&1 &
REPORTINFO "${MODULE_NAME}启动中。。。"

# 检测是否正常启动
CHECK_TIMES=3
for (( i = CHECK_TIMES; i > 0; i -- )); do
    START_SUCCESS=`ps -ef | grep -v grep | grep ${COMMAND_NAME} | grep ${DEPLOY_TARGET_PATH}/ | awk '{print $2}'`
    if [ "${START_SUCCESS}" != "" ]; then
        REPORTINFO "${MODULE_NAME}启动成功"
        exit 1
    fi
    sleep 5s
done
REPORTWARN "${MODULE_NAME}启动失败"
