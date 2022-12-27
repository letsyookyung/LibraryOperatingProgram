### Library Operating Program 도서 관리 프로그램
 
1) 도서관 관리자 모드  
   모드 선택 후 ID/비밀번호 로그인 체크 3번 
   - 전체 도서 목록 조회
     - bookList 출력
   - 도서 대여/반납 업데이트
     - 대여
       - bookList, member 별 checkOutHistory 업데이트
     - 반납
       - bookList, member 별 checkOutHistory 업데이트
   - 도서 구매 및 도서 목록 업데이트
     - 도서 구매
       - purchaseHistory 업데이트 및 도서 구매 예산 업데이트
       - bookList 업데이트
   - 로그아웃
     - 관리자/멤버 모드 선택 화면으로 돌아가기
   - 프로그램 종료
2) 도서관 멤버 모드
   모드 선택 후 ID/비밀번호 로그인 체크 3번 
   - 나의 도서 대여/반납 현황 보기
     - member 별 checkOutHistory 출력
   - 도서 검색
     - 도서명/저자명 선택하여, checkOut 현황 보여주기
   - 도서 대여
     - 도서명/저자명 선택
       - 저자명이 같은 경우 도서명으로 선택하도록 하기
     - bookList, member 별 checkOutHistory 업데이트
   - 도서 반납
     - bookList, member 별 checkOutHistory 업데이트
   - 로그아웃
       - 관리자/멤버 모드 선택 화면으로 돌아가기
   - 프로그램 종료
3) 사용자 등록
   - 관리자 등록
   - 멤버 등록
4) 프로그램 종료

--------

- enum class 써보기
- data class 써보기
- interface 써보기
- 사용하는 table 동기화 시키기
- 로그인 후 같은 ID 상태로 남아있기
