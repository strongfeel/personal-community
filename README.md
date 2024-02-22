# 💻 게시판 만들기

다양한 사용자들과 정보를 공유할 수 있는 게시판 서비스입니다. 

## 프로젝트 기능 및 설계

### ✅회원가입 기능
  - 사용자는 회원가입을 할 수 있다. 일반적으로 모든 사용자는 회원가입시 USER 권한 (일반 권한)을 지닌다. 
  - 회원가입시 아이디와 패스워드를 입력받으며, 아이디는 unique 해야한다.
    
### ✅로그인 기능
  - 로그인시 회원가입때 사용한 아이디와 패스워드가 일치해야만 로그인할 수 있다.
  - 소셜 로그인을 사용하여 로그인할 수 있다(추가 예정).

  1) 게시글 작성 기능
    - 로그인한 사용자는 권한에 관계 없이 글을 작성할 수 있다. 
    - 사용자는 게시글 제목(텍스트), 게시글 내용(텍스트)를 작성할 수 있다.

  3) 게시글 목록 조회 기능 
    - 로그인하지 않은 사용자를 포함한 모든 사용자는 게시글을 조회할 수 있다. 
    - 게시글은 최신순으로 기본 정렬된다.
    - 게시글 목록 조회시 응답에는 게시글 제목과 작성일, 작성자 아이디의 정보가 필요하다.
    - 게시글은 종류가 많을수 있으므로 20개씩 paging 처리를 한다.

  4) 특정 게시글 조회 기능
    - 로그인하지 않은 사용자를 포함한 모든 사용자는 게시글을 조회할 수 있다. 
    - 게시글 제목, 게시글 내용, 작성자, 작성일이 조회된다.
    
5) 게시글 수정, 삭제 기능 
    - 관리자는 모든 글을 삭제할 수 있다. 
    - 사용자는 본인의 게시글을 수정하거나 삭제할 수 있다.

### ✅댓글 기능
  1) 댓글 목록 조회 기능
    - 특정 게시글 조회시 댓글목록도 함께 조회가 된다. 다만 댓글은 많을 수 있기 때문에 별도의 API로 구성한다.
    - 로그인하지 않은 사용자를 포함한 모든 사용자가 댓글을 조회할 수 있다.
    - 댓글은 최신순으로만 정렬되며, 30개씩 paging 처리를 한다. 
    - 댓글 목록 조회시에는 댓글 작성자와 댓글 내용, 댓글 작성일의 정보가 필요하다.

  3) 댓글 작성 기능
    - 로그인한 사용자는 권한에 관계 없이 댓글을 작성할 수 있다. 
    - 사용자는 댓글 내용(텍스트)를 작성할 수 있다.

  4) 댓글 수정, 삭제 기능 
    - 관리자는 모든 댓글을 삭제할 수 있다. 
    - 작성자 본인의 댓글만 수정하거나 삭제할 수 있다.

## ERD 
![스크린샷 2024-02-22 201217](https://github.com/strongfeel/personal-community/assets/92740959/9b27a02e-03cf-4e04-be7d-0b4d651dbca4)

## Trouble Shooting
[go to the trouble shooting section](TROUBLE_SHOOTING.md)


### Tech Stack
<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>
