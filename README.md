### はじめに
- 目標
  - フロントエンドJavaScriptを利用したアプリケーション構築の土台を作成する
  - サーバーサイドはJavaを利用する

### フロントエンドJavascriptとは

TODO

### 目指す姿

従来のJava＋サーバーサイドレンダリングの開発に慣れ親しんだ開発者をフロントエンドJavaScriptを用いた開発にアジャストさせるのは学習等のコストが掛かりそうです。
そのため、従来どおりIDEにEclipseを用いて、npm等の知識を必要としないカタチ(開発者がnmp install hogehogeとかしなくていい)を目指しました。


### 利用するもの

- プロジェクト管理
  - Maven(Javaプロジェクト管理)
- 開発環境
  - Docker(PostgreSQL構築。 Kitematicを利用して簡易的に作成)
  - npm(Javascriptパッケージ管理)
  - Spring Tool Suite 4(EclipseベースのIDE。VSCodeの拡張機能もある)
  - Java8
- ライブラリ
  - Vue.js
  - SpringBoot(2.1.1)
  - Flyway(DBマイグレーション)
  - Google firebase(認証)
  - CoreUI(BootStrapVueベースのデザインテンプレート)
- バージョン管理
  - Git
  - BitBucket(プライベートリポジトリを無料で利用可能。1リポジトリ5ユーザまで利用可能※Githubは3ユーザ)
- 結合環境
  - Google App Engine
  - Google Cloud SQL

### ここではやらないこと、書かないこと

- Vueの解説(やらないというか、自分の知識が足らないので出来ません。ぐぐった方が良質な情報を得ることができると思います)
- Spring(SpringBoot)の解説
- 各ツールのインストール方法と設定の解説
  
  こちらは別途資料に起こす予定です



### 参考にした情報

- SpringBootとVueを組み合わせたMavenの構成を参考にしました

    [A Lovely Spring View: Spring Boot & Vue.js](https://blog.codecentric.de/en/2018/04/spring-boot-vuejs/)

- Vue Router、Firebaseを利用した認証機能の構築を参考にしました

    [SpringBoot+Vue.js+ElementUI+Firebaseでマスタ管理アプリ入門](https://qiita.com/shunp/items/abea7fa01e7a664c85da)

- Google App Engineへのデプロイ方法について参考にしました。フレキシブル環境はjarをデプロイできることを知りました。

  [Spring BootをGoogle App Engineフレキシブル環境へデプロイする](https://tosi-tech.net/2018/08/spring-boot-on-google-app-engine-flexible/)

