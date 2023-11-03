package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.newsModel


//받아온 데이터 형식을 보고 정해야함
//중요

//쉬운 예제
//"status": "ok",
//"totalResults": 11521,
//"articles": [
//{
//    "source": {
//    "id": null,
//    "name": "Investing.com"
//},
//    "author": "Cointelegraph",
//    "title": "Elon Musk slams NFTs but ends up arguing the case for Bitcoin Ordinals",
//    "description": "Elon Musk slams NFTs but ends up arguing the case for Bitcoin Ordinals",
//    "url": "https://www.investing.com/news/cryptocurrency-news/elon-musk-slams-nfts-but-ends-up-arguing-the-case-for-bitcoin-ordinals-3218163",
//    "urlToImage": "https://i-invdn-com.investing.com/news/Bitcoin_800x533_L_1535981090.jpg",
//    "publishedAt": "2023-11-02T06:20:11Z",
//    "content": "While publicly mocking non-fungible token (NFTs) during a podcast, Tesla (NASDAQ:TSLA) CEO and billionaire Elon Musk appears to have inadvertently highlighted the case for Bitcoin Ordinals, also know… [+312 chars]"
//},
class ItemListModel {
    //변수명, 넘어온 데이터 변수명과 일치
    val articles:MutableList<ItemModel>? = null

}