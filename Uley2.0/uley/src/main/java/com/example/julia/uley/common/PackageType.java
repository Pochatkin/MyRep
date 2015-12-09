package com.example.julia.uley.common;

/**
 * Created by Julia on 23.11.2015.
 */
public enum PackageType {
    //server client req
    REQ_SEARCH,
    REQ_SIGN_IN,
    REQ_SIGN_OUT,
    REQ_SIGN_UP,
    REQ_SEND_MESSAGE,

    //server response
    RESP_SIGN_IN_OK,
    RESP_SIGN_IN_FAILED,
    RESP_SIGN_UP_OK,
    RESP_SIGN_UP_USER_ALREADY_EXIST,
    RESP_SIGN_UP_LOGIN_FILTER_FAILED,
    RESP_SIGN_UP_PASS_FILTER_FAILED,
    RESP_SIGN_OUT_OK,
    RESP_MESSAGE_DELIVERED, // client response too
    RESP_MESSAGE_IN_QUEUE,
    RESP_MESSAGE_USER_NOT_FOUND,
    RESP_SEARCH_ANSWER,
    RESP_SERVER_ERROR;
}
