TRUNCATE TABLE users;

INSERT INTO users(id, code, name, role, status, password, registered) VALUES
(nextval('hibernate_sequence'), 'k_hoshino',  '星野 和也', 'admin', 'valid', 'test', now()),
(nextval('hibernate_sequence'), 't_ehira',    '江平 寿栄', 'admin', 'valid', 'test', now()),
(nextval('hibernate_sequence'), 'j_ikeda',    '池田 淳弥', 'admin', 'valid', 'test', now()),
(nextval('hibernate_sequence'), 'h_hagino',   '萩野 博之', 'admin', 'valid', 'test', now()),
(nextval('hibernate_sequence'), 'm_sekikawa', '関川 正之', 'admin', 'valid', 'test', now()),
(nextval('hibernate_sequence'), 'k_masuda',   '益田 健吾', 'admin', 'valid', 'test', now()),
(nextval('hibernate_sequence'), 'm_katagiri', '片桐 瑞樹', 'admin', 'valid', 'test', now());
