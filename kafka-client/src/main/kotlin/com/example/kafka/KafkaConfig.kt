package com.example.kafka

import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.DescribeConsumerGroupsOptions
import org.apache.kafka.clients.admin.MemberDescription
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct


/**
 *
 *@Auth yeyu
 *@Date 2024/12/26
 *
 */
@Component
class KafkaConfig {

//    @Resource
//    private val adminClient: AdminClient? = null

//    @Value("\${spring.kafka.bootstrap-servers:}")
    private val serverConfig: String = "172.22.3.108:31091,172.22.3.108:31092,172.22.3.108:31093"


    @PostConstruct
    fun testDemo(){
        val properties = Properties()
        // 配置Kafka服务的访问地址及端口号
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, serverConfig)
        properties[AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG] = "10000"
        properties[AdminClientConfig.DEFAULT_API_TIMEOUT_MS_CONFIG] = "10000"
        // 创建AdminClient实例
        val adminClient = AdminClient.create(properties)
        val tblockGroupId = "236_TBlock_group"
        val result = adminClient?.describeConsumerGroups(
            setOf(tblockGroupId),
            (DescribeConsumerGroupsOptions()).timeoutMs(10 * 1000)
        )
        if (result != null) {
            val arrayList = try {
                result.describedGroups()[tblockGroupId]?.get(30, TimeUnit.SECONDS)?.members()?.let {
                    ArrayList<MemberDescription>(
                        it
                    )
                }
            } catch (e: Exception) {
                println(e.message)
            }
            println(arrayList)
        }

        val p2 = Properties()
        p2[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = serverConfig
        p2[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        p2[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        p2[ConsumerConfig.GROUP_ID_CONFIG] = tblockGroupId
        val kafkaConsumer = KafkaConsumer<String, String>(p2)
        kafkaConsumer.subscribe(listOf("236_TBlock"))

        while (true) {
            for (record in kafkaConsumer.poll(Duration.ofMillis(300))) {
                println("====================")
                println(record.value())
                println(record.key())
                println("====================")
            }
        }
    }
}