package com.zzr.jetpacktest.module_logic.model

/**
 * {
"apkLink":"",
"audit":1,
"author":"",
"canEdit":false,
"chapterId":502,
"chapterName":"自助",
"collect":false,
"courseId":13,
"desc":"",
"descMd":"",
"envelopePic":"",
"fresh":true,
"id":15026,
"link":"https://www.jianshu.com/p/70d5785ee4c3",
"niceDate":"10小时前",
"niceShareDate":"10小时前",
"origin":"",
"prefix":"",
"projectLink":"",
"publishTime":1598670686000,
"realSuperChapterId":493,
"selfVisible":0,
"shareDate":1598670686000,
"shareUser":"彭旭锐",
"superChapterId":494,
"superChapterName":"广场Tab",
"tags":[

],
"title":"Android | 面试必问的 Handler，你确定不看看？",
"type":0,
"userId":30587,
"visible":1,
"zan":0
}
 */
data class Article(
    val link: String,
    val niceDate: String,
    val shareUser: String,
    val title: String
)