DropWizard 求人情報検索・登録システム
==================================
求人情報の検索・登録ができるシステム  
Java + DropWizard  
MariaDB


前提条件
----------------------------------
【MariaDB】  
・インストールされていること  
・データベース「dropwizard_practice」がローカル環境に作成されていること  
・以下のユーザが作成されていること  
ユーザ名：'testUser002'@'localhost'  
パスワード：password002  


事前準備
----------------------------------
【DBマイグレ編】  
・テーブル、データの作成コマンド  
java -jar build/libs/JobOffer.jar db migrate setting/jobOffer.yml  


実行
----------------------------------
java -jar build/libs/JobOffer.jar server setting/jobOffer.yml  

