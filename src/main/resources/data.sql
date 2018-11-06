INSERT INTO `login`.`sys_user` (`id`, `password`, `username`) VALUES ('1', '123', 'aa');
INSERT INTO `login`.`sys_user` (`id`, `password`, `username`) VALUES ('2', '123', 'bb');

INSERT INTO `login`.`sys_role` (`id`, `name`) VALUES ('1', 'ROLE_USER');
INSERT INTO `login`.`sys_role` (`id`, `name`) VALUES ('2', 'ROLE_ADMIN');

INSERT INTO `login`.`sys_user_roles` (`sys_user_id`, `roles_id`) VALUES ('1', '1');
INSERT INTO `login`.`sys_user_roles` (`sys_user_id`, `roles_id`) VALUES ('2', '2');
