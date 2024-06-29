--liquibase formatted sql
--changeset erickmendes:load_table_acao_tipo
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM ACAO_TIPO
INSERT INTO acao_tipo (id, nome, descricao) VALUES
(nextval('acao_tipo_id_seq'), 'E-mail', 'Enviar notificação via e-mail'),
(nextval('acao_tipo_id_seq'), 'Gerar ação fiscal SIGT', 'Gerar ação fiscal SIGT'),
(nextval('acao_tipo_id_seq'), 'Enviar mensagem no DTE', 'Enviar mensagem no DTE');
--rollback DELETE * FROM ACAO_TIPO;