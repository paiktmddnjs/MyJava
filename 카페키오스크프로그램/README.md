# ☕ 카페 키오스크 프로그램 (Cafe Kiosk Program)

Java와 MySQL을 활용한 카페 키오스크 관리 시스템입니다. 회원 관리, 메뉴 주문, 장바구니, 그리고 스탬프/쿠폰 시스템을 제공합니다. 사용자는 CLI(Console) 환경에서 메인 흐름을 진행하며, 메뉴 선택 시 GUI(Swing)를 통해 직관적으로 수량을 선택할 수 있습니다.

## 🚀 주요 기능

- **회원 관리**: 전화번호를 이용한 간편 로그인 및 자동 회원가입.
- **스탬프 & 쿠폰 시스템**:
  - 음료 구매 시 스탬프 적립.
  - 스탬프 8개 적립 시 무료 음료 쿠폰 자동 발행.
  - 결제 시 쿠폰 사용 가능 (5,000원 할인 또는 무료 처리).
- **하이브리드 UI**:
  - **CLI (Command Line Interface)**: 메인 메뉴, 로그인, 장바구니 확인, 결제 진행.
  - **GUI (Swing)**: 메뉴 목록 보기 및 수량 선택 다이얼로그.
- **장바구니 기능**: 선택한 메뉴의 추가 및 삭제, 실시간 총 금액 계산.
- **데이터베이스 연동**: MySQL을 사용하여 회원 정보, 메뉴 데이터, 주문 내역을 영구 저장.

## 🛠 기술 스택

- **Language**: Java (JDK 17+)
- **UI Framework**: Swing (GUI), Console (CLI)
- **Database**: MySQL
- **Library**: JDBC (MySQL Connector/J)

## 📂 프로젝트 구조

```text
src/
├── Run/            # 프로그램 실행 (Main class)
├── View/           # CLI 및 GUI 화면 구현
├── Controller/     # 비즈니스 로직 제어
├── Service/        # 데이터 처리 서비스 (Member, Menu 서비스)
├── DAO/            # 데이터베이스 접근 객체 (Data Access Object)
├── VO/             # 데이터 객체 (Value Object: Member, Menu, Order 등)
├── Common/         # 공통 유틸리티 (DB 연동 설정: DBUtil)
└── ButtonDialog/   # GUI용 커스텀 다이얼로그
```

## ⚙️ 설정 및 실행 방법

### 1. 데이터베이스 설정
MySQL에 접속하여 아래와 같이 데이터베이스를 생성하고 테이블을 구성해야 합니다. (JDBC 설정 기준)
- **DB Name**: `cafe_kiosk`
- **User**: `root1` (또는 `DBUtil.java`에서 수정 가능)
- **Password**: `password` (또는 `DBUtil.java`에서 수정 가능)

### 2. JDBC 드라이버 추가
프로젝트의 Build Path에 `mysql-connector-java-x.x.x.jar` 파일이 포함되어 있어야 합니다.

### 3. 프로그램 실행
`Run.java` 파일을 실행하면 키오스크 프로그램이 시작됩니다.

## �️ 시연 이미지

| 초기 항목 | 항목 선택 | 카페 메뉴 선택 |
| :---: | :---: | :---: |
| <img width="289" height="143" alt="화면 캡처 2026-03-04 134507" src="https://github.com/user-attachments/assets/7886979f-6c26-4d4e-aa14-a1e45dc599ac" /> | <img width="293" height="114" alt="화면 캡처 2026-03-04 134547" src="https://github.com/user-attachments/assets/7c85f44d-e984-49d4-97c6-d31c017e76a8" /> | <img width="491" height="295" alt="화면 캡처 2026-03-04 134623" src="https://github.com/user-attachments/assets/1b57b188-52b6-4a9c-b244-5aa039a3c747" /> |
| 장바구니 |  결제 및 스탬프 |
| <img width="340" height="107" alt="화면 캡처 2026-03-04 134653" src="https://github.com/user-attachments/assets/77c1421b-1b53-4688-8fe5-cf31b589dc6b" /> | <img width="327" height="78" alt="화면 캡처 2026-03-04 134715" src="https://github.com/user-attachments/assets/d08c9cb0-d5ba-4d06-b9ff-a1e8ade70548" /> |

## 📝 사용 예시

1. **프로그램 시작**: `Run.java` 실행 후 `1번`을 눌러 전화번호로 로그인합니다.
2. **메뉴 보기**: `2번`을 눌러 메뉴 목록을 확인합니다 (Swing 창 오픈).
3. **수량 선택**: 원하는 메뉴 클릭 후 수량을 조절하여 장바구니에 담습니다.
4. **결제 및 적립**: `4번` 결제하기를 통해 주문 완료 및 스탬프 적립을 확인합니다.

