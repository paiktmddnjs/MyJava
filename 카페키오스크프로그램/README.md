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

## 📝 사용 예시
1. 프로그램 시작 후 `1번`을 눌러 전화번호로 로그인합니다.
2. `2번`을 눌러 메뉴를 확인합니다. (Swing 창이 열립니다.)
3. 원하는 메뉴를 클릭하고 수량을 입력하여 장바구니에 담습니다.
4. `4번` 결제하기를 통해 주문을 완료합니다. (스탬프 적립 확인 가능)
