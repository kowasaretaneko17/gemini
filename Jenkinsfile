#!groovy
def project_token = 'ce9a81e1409b427a9056990152bc48e4'
properties([
        gitLabConnection('gemini'),
        pipelineTriggers([
                [
                        $class                        : 'GitLabPushTrigger',
                        triggerOnPush                 : true,
                        triggerOnMergeRequest         : true,
                        triggerOpenMergeRequestOnPush : "never",
                        triggerOnNoteRequest          : true,
                        noteRegex                     : "regex？",
                        skipWorkInProgressMergeRequest: true,
                        secretToken                   : project_token,
                        ciSkip                        : false,
                        setBuildDescription           : true,
                        addNoteOnMergeRequest         : true,
                        addCiMessage                  : true,
                        addVoteOnMergeRequest         : true,
                        acceptMergeRequestOnSuccess   : true,
                        branchFilterType              : "RegexBasedFilter",
                        sourceBranchRegex             : "^.*(dev)+.*\$",
                        targetBranchRegex             : "^.*(dev)+.*\$"
                ]
        ])
])
pipeline {
    //在任何可用的代理上执行Pipeline
    agent any
    //常量参数，初始确定后一般不需更改
    environment {
        //部署的目标服务器地址(部署用)
        server = '10.96.2.137'
        //jar包相对路径(部署用)
        jarLocation = '/var/lib/jenkins/workspace/gemini/target/gemini-0.0.1-SNAPSHOT'
        //jar包的前缀ignore(部署用)
        jarLocationPrefix = '/var/lib/jenkins/workspace/gemini/target/'
        //pom.xml的相对路径(打包用)
        pomPath = 'pom.xml'
//        //钉钉机器人(发送信息用)
//        dingTaskUrl = 'https://oapi.dingtalk.com/robot/send?access_token=d65628ed2dd6e603eca29994bdaa2c62a2b0acf065d56c0615f3bbfd0d392e60'
//        //是否Merge请求的标记(流程控制用)
//        mergeMark = '0'
//        //git项目id，目前多流水么有办法拿到，只能先定义(sonar用)
//        projectId = '335'
//        //git项目名称(sonar用)
//        refName = 'cicd_test'
//        //项目名称(接口测试平台用)
//        ProjectName = 'DEVOPS_DEMO'
//        //接口测试接口(接口测试平台用)
//        apiTestUrl = 'http://10.11.0.150:11111/start'
    }
    stages {
        stage('jenkins要开始构建啦') {
            steps {
                script {
                    echo "gemini测试啦"
                }
            }
        }
        stage('部署环境') {
            steps {
                //不同的测试环境根据服务器不同进行区分，但此处代码相似
                script {
                    echo "starting deploy to server......"
                    sh "mvn -f ${pomPath} clean package -Dautoconfig.skip=true -Dmaven.test.skip=true"
                    sh "/bin/bash /app/script/AutoDeploy.sh restart"
                }
            }
        }
    }
}