package com.dj.gittrends.common.exceptions

class HttpException(val errorCode: Int, val errorMessage: String) : Exception()
