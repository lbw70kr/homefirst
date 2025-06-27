CREATE TABLE `posts` (
  `id` BIGINT PRIMARY KEY COMMENT '게시글 고유 ID',
  `board_id` BIGINT NOT NULL COMMENT '게시판 ID (어떤 게시판 소속인지)',
  `user_id` BIGINT COMMENT '회원 작성자 ID (비회원이면 null)',
  `writer_name` VARCHAR(100) COMMENT '비회원 작성자 이름 (회원일 경우 null)',
  `password_hash` VARCHAR(255) COMMENT '비회원용 비밀번호 해시 (해시된 값만 저장)',
  `title` VARCHAR(255) NOT NULL COMMENT '게시글 제목',
  `content` TEXT NOT NULL COMMENT '게시글 본문 내용',
  `status` VARCHAR(20) NOT NULL COMMENT '글 상태: draft(작성 중), published(게시됨), hidden(숨김), deleted(삭제됨)',
  `is_private` BOOLEAN NOT NULL DEFAULT false COMMENT '비공개 여부 (true일 경우 비공개 글)',
  `view_count` INT DEFAULT 0 COMMENT '조회수',
  `pinned_at` DATETIME COMMENT '상단 고정 시간 (공지글처럼 게시판 상단에 고정 표시할 경우 사용)',
  `ip_address` VARCHAR(45) COMMENT '작성자 IP (익명 기반일 경우에만 저장)',
  `manager_id` BIGINT COMMENT '담당자 ID (게시글을 관리하는 운영자 등)',
  `created_by` VARCHAR(50) COMMENT '등록자 식별자 (user_id와 다를 수 있음, 예: 시스템 자동 생성 등)',
  `created_at` DATETIME COMMENT '등록 일시',
  `updated_by` VARCHAR(50) COMMENT '수정자 식별자',
  `updated_at` DATETIME COMMENT '수정 일시',
  `deleted_at` DATETIME COMMENT '삭제 일시 (soft delete 시점)'
);

CREATE TABLE `boards` (
  `id` BIGINT PRIMARY KEY COMMENT '게시판 고유 ID',
  `name` VARCHAR(100) UNIQUE NOT NULL COMMENT '게시판 이름 (고유한 시스템 식별자)',
  `description` TEXT COMMENT '게시판 설명 (관리자나 사용자에게 보여줄 설명)',
  `is_active` BOOLEAN NOT NULL DEFAULT true COMMENT '게시판 사용 여부 (false일 경우 숨김)',
  `allow_pinned` BOOLEAN NOT NULL DEFAULT true COMMENT '공지글 기능 허용 여부 (pinned_at 사용 가능 여부)',
  `allow_comment` BOOLEAN NOT NULL DEFAULT true COMMENT '댓글 작성 허용 여부',
  `allow_attachment` BOOLEAN NOT NULL DEFAULT true COMMENT '파일 첨부 허용 여부',
  `allow_comment_attachment` BOOLEAN NOT NULL DEFAULT false COMMENT '댓글에 파일 첨부 허용 여부',
  `allow_anonymous_post` BOOLEAN NOT NULL DEFAULT false COMMENT '비회원 글쓰기 허용 여부',
  `board_type` VARCHAR(20) COMMENT '게시판 유형 구분 (예: 일반, QNA, FAQ 등)',
  `display_order` INT DEFAULT 0 COMMENT '관리자 UI 등에서의 노출 정렬 순서',
  `created_by` VARCHAR(50) COMMENT '게시판 생성자 ID',
  `created_at` DATETIME COMMENT '게시판 생성 시각',
  `updated_by` VARCHAR(50) COMMENT '게시판 수정자 ID',
  `updated_at` DATETIME COMMENT '게시판 수정 시각'
);

CREATE TABLE `categories` (
  `id` BIGINT PRIMARY KEY COMMENT '카테고리 고유 ID',
  `parent_category_id` BIGINT COMMENT '상위 카테고리 ID (없으면 null) → 트리 구조 표현',
  `name` VARCHAR(100) NOT NULL COMMENT '카테고리 이름 (사용자에게 표시됨)',
  `is_active` BOOLEAN NOT NULL DEFAULT true COMMENT '카테고리 사용 여부 (false면 숨김 처리)',
  `display_order` INT DEFAULT 0 COMMENT '노출 순서 (UI 정렬용)',
  `created_by` VARCHAR(50) COMMENT '카테고리 생성자 ID',
  `created_at` DATETIME COMMENT '생성 시각',
  `updated_by` VARCHAR(50) COMMENT '수정자 ID',
  `updated_at` DATETIME COMMENT '수정 시각'
);

CREATE TABLE `post_categories` (
  `id` BIGINT PRIMARY KEY COMMENT '연결 고유 ID',
  `post_id` BIGINT NOT NULL COMMENT '게시글 ID',
  `category_id` BIGINT NOT NULL COMMENT '카테고리 ID',
  `created_at` DATETIME COMMENT '연결 생성 시각'
);

CREATE TABLE `users` (
  `id` BIGINT PRIMARY KEY COMMENT '사용자 고유 ID',
  `username` VARCHAR(100) COMMENT '사용자 이름 또는 시스템 식별자'
);

CREATE UNIQUE INDEX `uniq_post_category` ON `post_categories` (`post_id`, `category_id`);

ALTER TABLE `post_categories` COMMENT = '게시글과 카테고리 간 다대다(N:N) 관계를 표현하는 연결 테이블. 중복 연결 방지를 위해 Unique 인덱스 설정.';

ALTER TABLE `posts` ADD FOREIGN KEY (`board_id`) REFERENCES `boards` (`id`);

ALTER TABLE `posts` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `posts` ADD FOREIGN KEY (`manager_id`) REFERENCES `users` (`id`);

ALTER TABLE `categories` ADD FOREIGN KEY (`parent_category_id`) REFERENCES `categories` (`id`);

ALTER TABLE `post_categories` ADD FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);

ALTER TABLE `post_categories` ADD FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);