package org.whispersystems.textsecuregcm.util;

import java.net.URI;

import org.whispersystems.textsecuregcm.configuration.DynamoDbClientConfiguration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamoDbFromConfig {

  public static DynamoDbClient client(DynamoDbClientConfiguration config, AwsCredentialsProvider credentialsProvider) {
    return DynamoDbClient.builder()
        //.endpointOverride(URI.create("http://localhost:8009"))
        .region(Region.of(config.getRegion()))
        //.credentialsProvider(credentialsProvider)
        .credentialsProvider(StaticCredentialsProvider.create(
          AwsBasicCredentials.create("AKIAYFDZTKOV5OKLKJNE", "EVNVJ0oCyTCiVbXXpuILHnRMceoEoYMtR7agXMJm")))
        .overrideConfiguration(ClientOverrideConfiguration.builder()
            .apiCallTimeout(config.getClientExecutionTimeout())
            .apiCallAttemptTimeout(config.getClientRequestTimeout())
            .build())
        .build();
  }

  public static DynamoDbAsyncClient asyncClient(
      DynamoDbClientConfiguration config,
      AwsCredentialsProvider credentialsProvider) {
    return DynamoDbAsyncClient.builder()
        //.endpointOverride(URI.create("http://localhost:8009"))
        .region(Region.of(config.getRegion()))
        //.credentialsProvider(credentialsProvider)
        .credentialsProvider(StaticCredentialsProvider.create(
          AwsBasicCredentials.create("AKIAYFDZTKOV5OKLKJNE", "EVNVJ0oCyTCiVbXXpuILHnRMceoEoYMtR7agXMJm")))
        .overrideConfiguration(ClientOverrideConfiguration.builder()
            .apiCallTimeout(config.getClientExecutionTimeout())
            .apiCallAttemptTimeout(config.getClientRequestTimeout())
            .build())
        .build();
  }
}
