# 빌드 #
### requierment ###
* java 버젼 8 이상
* maven 빌드 가능한 시스템
* 자신의 환경에 맞춰 'application.yml'의 이하 설정을 수정할것
  * spring.datasource.url
  * spring.datasource.username
  * spring.datasource.password
### build command ###
`````````````
mvn pakage
`````````````
---------------------------
# 실행 #
### requierment ###
* java 버젼 8이상
* 톰캣
* mysql
### 실행 방법 ###
1. 빌드된 war 이름을 ROOT.war로 변경
2. %tomcat_install_path%/webapp 폴더에 빌드한 war 파일 배포
3. %tomcat_install_path%/bin/start.bat 실행
4. API별 DTO에 맞게 리퀘스트
5. 리스폰스 및 DB에 접속해 변경내역을 확인한다.
--------------------------
# API #
* 모든 id는 UUID 형식
## POST: /event ##
### DTO ###
 ``````````````
    private EventType type; // REVIEW 
    private EventAction action;  //  ADD, MOD, DELETE
    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;
``````````````
## GET:  /point ##
### DTO ###
``````````
  String userId;
``````````

# ETC #
* point 증감 이력은 %tomcat_install_path%/logs/PointChange.log에 남습니다.

