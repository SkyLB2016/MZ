//package com.sky.oa.utils.http
//
///**
// * Created by SKY on 2017/6/1.
// * 网络请求类
// */
//class HttpUtils private constructor() : BaseHttp() {
//    companion object {
//        private var utils: HttpUtils? = null
//
//        val instance: HttpUtils
//            get() {
//                if (utils == null)
//                    synchronized(HttpUtils::class.java) {
//                        if (utils == null) utils = HttpUtils()
//                    }
//                return utils!!
//            }
//    }
//
//    init {
//        initClient()
//        initRetrofit()
//    }
//
////    fun login(params: LoginParams): Observable<ApiResponse<LoginEntity>> {
////        return retrofit!!.create(IHttpUrl::class.java).login(params)
////    }
////
////    fun getMuke(): Observable<ApiResponse<CourseEntity>> {
////        return Retrofit.Builder()
////                .baseUrl(HttpUrl.URL_MUKE)
////                .addConverterFactory(GsonConverterFactory.create())
////                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
////                .client(client)
////                .build()
////                .create(IHttpUrl::class.java)
////                .getMuKe()
////    }
//}
