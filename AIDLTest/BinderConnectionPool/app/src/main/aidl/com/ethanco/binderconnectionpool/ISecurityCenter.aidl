package com.ethanco.binderconnectionpool;

//加解码
interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}
