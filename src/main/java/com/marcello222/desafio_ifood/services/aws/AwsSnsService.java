package com.marcello222.desafio_ifood.services.aws;


import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {

    AmazonSNS amazonSnsClient;

    Topic catalogEventsTopic;

    public AwsSnsService(AmazonSNS amazonSnsClient, @Qualifier("catalogEventsTopic") Topic catalogEventsTopic) {
        this.amazonSnsClient = amazonSnsClient;
        this.catalogEventsTopic = catalogEventsTopic;
    }

    public void publish(MessageDto message) {
       this.amazonSnsClient.publish(catalogEventsTopic.getTopicArn(), message.toString());
    }

    public void setAmazonSNS(AmazonSNS amazonSnsClient) {
        this.amazonSnsClient = amazonSnsClient;
    }

}
