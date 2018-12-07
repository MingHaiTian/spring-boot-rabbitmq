package com.example.rabbitmq.command;

/**
 * @Description:测试指令
 * @Author:tianminghai
 * @Date:1:34 PM 2018/11/1
 */
public class TestCommand {
  private String key;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  @Override
  public String toString() {
    return "TestCommand{" +
      "key='" + key + '\'' +
      '}';
  }
}
