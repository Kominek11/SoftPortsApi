INSERT INTO versao (id, data_download, data_origem, origem_nome, processo_id, referencia_carga, registro_origem_tipo) VALUES
(1, '2023-09-20 00:00:00.000', '2023-09-09 00:00:00.000', 'GOV_SIMPLES', 1, 1, 'gov_simples'),
(2, '2023-09-20 00:00:00.000', '2023-09-09 00:00:00.000', 'GOV_EMPRESA', 2, 1, 'gov_empresa'),
(3, '2023-09-20 00:00:00.000', '2023-09-09 00:00:00.000', 'GOV_SOCIO', 3, 1, 'gov_socio'),
(4, '2023-10-25 00:00:00.000', '2023-10-13 00:00:00.000', 'GOV_SIMPLES', 1, 2, 'gov_simples'),
(5, '2023-10-25 00:00:00.000', '2023-10-13 00:00:00.000', 'GOV_EMPRESA', 2, 2, 'gov_empresa'),
(6, '2023-10-25 00:00:00.000', '2023-10-13 00:00:00.000', 'GOV_SOCIO', 3, 2, 'gov_socio');

INSERT INTO gov_simples (id, versao_id, cnpj, opcao_simples, data_opcao_simples, data_exclusao_simples, opcao_mei, data_opcao_mei, data_exclusao_mei) VALUES
(1, 1, '32272034000121', 'SIM', '2018-12-17', NULL, 'SIM', '2018-12-17', NULL),
(2, 1, '43284222000196', 'SIM', '2021-08-25', NULL, 'SIM', '2021-08-25', NULL),
(3, 1, '43502444000138', 'SIM', '2021-09-13', NULL, 'SIM', '2021-09-13', NULL),
(4, 1, '41067804000195', 'SIM', '2021-03-03', NULL, 'NÃO', NULL, NULL),
(5, 1, '08696698000214', 'NÃO', '2021-01-01', '2022-12-31', 'NÃO', NULL, NULL),
(6, 4, '32272034000121', 'SIM', '2018-12-17', NULL, 'SIM', '2018-12-17', NULL),
(7, 4, '43284222000196', 'SIM', '2021-08-25', NULL, 'SIM', '2021-08-25', NULL),
(8, 4, '43502444000138', 'SIM', '2021-09-13', NULL, 'SIM', '2021-09-13', NULL),
(9, 4, '41067804000195', 'SIM', '2021-03-03', NULL, 'NÃO', NULL, NULL),
(10, 4, '08696698000214', 'NÃO', '2021-01-01', '2022-12-31', 'NÃO', NULL, NULL);

INSERT INTO gov_empresa (id, versao_id, cnpj, razao_social, natureza_juridica, qualificacao_responsavel, capital_social, porte_empresa, ente_federativo_responsavel, id_matriz_filial, nome_fantasia, situacao_cadastral, data_situacao_cadastral, motivo_situacao_cadastral, data_inicio_atividade, cnae_fiscal_principal, cnae_fiscal_secundaria, tipo_logradouro, logradouro, numero, complemento, bairro, cep, uf, municipio, ddd_1, telefone_1, ddd_2, telefone_2, correio_eletronico, situacao_especial, data_situacao_especial) VALUES
(1, 2, '32272034000121', 'ALEXANDRE SANTOS STOPA 36729070811', 'Empresário (Individual)', 'Empresário', 5000.0, 'MICRO EMPRESA', '', 'MATRIZ', 'HIRANT SANAZAR HABIB', 'INAPTA', '2021-03-26', 'OMISSAO DE DECLARACOES', '2018-12-17', '5611201', '', 'AVENIDA', 'PREFEITO HIRANT SANAZAR', '915', '', 'UMUARAMA', '06030095', 'SP', 'OSASCO', 11, '86302477', 0, '', 'anderson.43@yahoo.com', '', NULL),
(2, 2, '43284222000196', '43.284.222 CRISTIANE ROBERTA ALCEBIADES', 'Empresário (Individual)', 'Empresário', 10000.0, 'MICRO EMPRESA', '', 'MATRIZ', 'CAIQUE', 'ATIVA', '2021-08-25', 'SEM MOTIVO', '2021-08-25', '4712100', '', 'RUA', 'THEREZINHA DE JESUS BONILHA', '67', '', 'NOVO OSASCO', '06142010', 'SP', 'OSASCO', 11, '43112085', 0, '', 'DERES23@HOTMAIL.COM', '', NULL),
(3, 2, '41067804000195', 'TAA - OBGYN SAUDE LTDA', 'Sociedade Empresária Limitada', 'Sócio-Administrador', 110000.0, 'MICRO EMPRESA', '', 'MATRIZ', 'TAA - OBGYN SAUDE EIRELI', 'ATIVA', '2021-03-03', 'SEM MOTIVO', '2021-03-03', '8630503', '', 'RUA', 'SEBASTIAO DE MELLO DIAS', '264', '', 'JAGUARIBE', '06050170', 'SP', 'OSASCO', 11, '34488610', 0, '', 'SERECOMCONT@HOTMAIL.COM', '', NULL),
(4, 2, '08696698000214', 'ROBSON SIMOES ALVES PINTO', 'Empresário (Individual)', 'Empresário', 50000.0, 'MICRO EMPRESA', '', 'FILIAL', 'WAVE COMERCIO DE EQUIPAMENTO DE COZINHA INDUSTRIAL', 'ATIVA', '2022-07-11', 'SEM MOTIVO', '2022-07-11', '4789099', '4759899', 'RUA', 'EMILIO RIZZO', '25-580', 'LOJA  LOJA 7 Q.29', 'JARDIM D''ABRIL', '06040120', 'SP', 'OSASCO', 11, '55846989', 11, '82731594', 'OFFICINADACOZINHA@GMAIL.COM', '', NULL),
(5, 2, '50551243000196', 'CASA FERNANDES CONSULTORIA IMOBILIARIA LTDA', 'Sociedade Empresária Limitada', 'Sócio-Administrador', 1000.0, 'MICRO EMPRESA', '', 'MATRIZ', 'CASA FERNANDES', 'ATIVA', '2023-05-04', 'SEM MOTIVO', '2023-05-04', '6822600', '6821801,6821802', 'AVENIDA', 'MANOEL PEDRO PIMENTEL', '103', 'APT   44                  ANDAR 4 TORRE 3', 'CONTINENTAL', '06020194', 'SP', 'OSASCO', 41, '97880145', 0, '', 'MEUCNPJ@CONTABILIZEI.COM.BR', '', NULL),
(6, 5, '32272034000121', 'ALEXANDRE SANTOS STOPA 36729070811', 'Empresário (Individual)', 'Empresário', 5000.0, 'MICRO EMPRESA', '', 'MATRIZ', 'HIRANT SANAZAR HABIB', 'INAPTA', '2021-03-26', 'OMISSAO DE DECLARACOES', '2018-12-17', '5611201', '', 'AVENIDA', 'PREFEITO HIRANT SANAZAR', '915', '', 'UMUARAMA', '06030095', 'SP', 'OSASCO', 11, '86302477', 0, '', 'anderson.43@yahoo.com', '', NULL),
(7, 5, '43284222000196', '43.284.222 CRISTIANE ROBERTA ALCEBIADES', 'Empresário (Individual)', 'Empresário', 10000.0, 'MICRO EMPRESA', '', 'MATRIZ', 'CAIQUE', 'ATIVA', '2021-08-25', 'SEM MOTIVO', '2021-08-25', '4712100', '', 'RUA', 'THEREZINHA DE JESUS BONILHA', '67', '', 'NOVO OSASCO', '06142010', 'SP', 'OSASCO', 11, '43112085', 0, '', 'DERES23@HOTMAIL.COM', '', NULL),
(8, 5, '41067804000195', 'TAA - OBGYN SAUDE LTDA', 'Sociedade Empresária Limitada', 'Sócio-Administrador', 110000.0, 'MICRO EMPRESA', '', 'MATRIZ', 'TAA - OBGYN SAUDE EIRELI', 'ATIVA', '2021-03-03', 'SEM MOTIVO', '2021-03-03', '8630503', '', 'RUA', 'SEBASTIAO DE MELLO DIAS', '264', '', 'JAGUARIBE', '06050170', 'SP', 'OSASCO', 11, '34488610', 0, '', 'SERECOMCONT@HOTMAIL.COM', '', NULL),
(9, 5, '08696698000214', 'ROBSON SIMOES ALVES PINTO', 'Empresário (Individual)', 'Empresário', 50000.0, 'MICRO EMPRESA', '', 'FILIAL', 'WAVE COMERCIO DE EQUIPAMENTO DE COZINHA INDUSTRIAL', 'ATIVA', '2022-07-11', 'SEM MOTIVO', '2022-07-11', '4789099', '4759899', 'RUA', 'EMILIO RIZZO', '25-580', 'LOJA  LOJA 7 Q.29', 'JARDIM D''ABRIL', '06040120', 'SP', 'OSASCO', 11, '55846989', 11, '82731594', 'OFFICINADACOZINHA@GMAIL.COM', '', NULL),
(10, 5, '50551243000196', 'CASA FERNANDES CONSULTORIA IMOBILIARIA LTDA', 'Sociedade Empresária Limitada', 'Sócio-Administrador', 1000.0, 'MICRO EMPRESA', '', 'MATRIZ', 'CASA FERNANDES', 'ATIVA', '2023-05-04', 'SEM MOTIVO', '2023-05-04', '6822600', '6821801,6821802', 'AVENIDA', 'MANOEL PEDRO PIMENTEL', '103', 'APT 44 ANDAR 4 TORRE 3', 'CONTINENTAL', '06020194', 'SP', 'OSASCO', 41, '97880145', 0, '', 'MEUCNPJ@CONTABILIZEI.COM.BR', '', NULL);

INSERT INTO gov_socio (id, versao_id, cnpj, identificador_socio, nome_socio, documento_socio, qualificacao_socio, data_entrada_sociedade, pais_socio_estrangeiro, representante_legal_documento, representante_legal_nome, representante_legal_qualificacao, faixa_etaria) VALUES
(1, 3, '41067804000195', 'Pessoa Física', 'TAISSA ALTIERI DO AMARAL QUATTRONE', '***320018**', 'Sócio-Administrador', '2021-03-03', NULL, '***000000**', '', 'Năo informada', 'Entre 31 a 40 anos'),
(2, 3, '50551243000196', 'Pessoa Física', 'PEDRO FERNANDES DA SILVA JUNIOR', '***650268**', 'Sócio', '2023-05-04', NULL, '***000000**', '', 'Năo informada', 'Entre 51 a 60 anos'),
(3, 3, '50551243000196', 'Pessoa Física', 'BEATRIZ JACIUK SILVA', '***815108**', 'Sócio', '2023-05-04', NULL, '***000000**', '', 'Năo informada', 'Entre 21 a 30 anos'),
(4, 3, '50551243000196', 'Pessoa Física', 'ELAINE CRISTINA JACIUK FERNANDES', '***092568**', 'Sócio-Administrador', '2023-05-04', NULL, '***000000**', '', 'Năo informada', 'Entre 41 a 50 anos'),
(5, 3, '53416160000263', 'Pessoa Física', 'SOLANGE DECARLI FEDERICI', '***348718**', 'Sócio', '1990-09-13', NULL, '***000000**', '', 'Năo informada', 'Entre 51 a 60 anos'),
(6, 6, '32272034000121', 'Pessoa Física', 'TAISSA ALTIERI DO AMARAL QUATTRONE', '***320018**', 'Sócio-Administrador', '2021-03-03', NULL, '***000000**', '', 'Năo informada', 'Entre 31 a 40 anos'),
(7, 6, '43284222000196', 'Pessoa Física', 'PEDRO FERNANDES DA SILVA JUNIOR', '***650268**', 'Sócio', '2023-05-04', NULL, '***000000**', '', 'Năo informada', 'Entre 51 a 60 anos'),
(8, 6, '41067804000195', 'Pessoa Física', 'BEATRIZ JACIUK SILVA', '***815108**', 'Sócio', '2023-05-04', NULL, '***000000**', '', 'Năo informada', 'Entre 21 a 30 anos'),
(9, 6, '08696698000214', 'Pessoa Física', 'ELAINE CRISTINA JACIUK FERNANDES', '***092568**', 'Sócio-Administrador', '2023-05-04', NULL, '***000000**', '', 'Năo informada', 'Entre 41 a 50 anos'),
(10, 6, '50551243000196', 'Pessoa Física', 'SOLANGE DECARLI FEDERICI', '***348718**', 'Sócio', '1990-09-13', NULL, '***000000**', '', 'Năo informada', 'Entre 51 a 60 anos');

INSERT INTO processamento (id, executado, status, detalhes, versao_id, data_inicio, data_conclusao, total_registros, total_registros_pai) VALUES
(nextval('processamento_id_seq'), true, 'SUCESSO', 'Total de registros processados 5', 5, '2023-11-27 10:44:07.845', '2023-11-27 11:22:41.725', 5, 5),
(nextval('processamento_id_seq'), true, 'SUCESSO', 'Total de registros processados 5', 5, '2023-11-27 10:44:07.845', '2023-11-27 11:22:41.725', 5, 5),
(nextval('processamento_id_seq'), true, 'ERRO', 'Erro', 2, '2023-11-27 10:44:07.845', null, 0, 0),
(nextval('processamento_id_seq'), true, 'SUCESSO', 'Total de registros processados x', 6, '2023-11-27 10:44:07.845', '2023-11-27 11:44:07.845', 5, 5);

INSERT INTO ocorrencia (id, data, processamento_id, ocorrencia_tipo_id, registro_origem_tipo, registro_destino, registro_destino_tipo, email_notificacao, registro_origem, detalhes, registro_pai_tipo, registro_pai, total_registros, registro_destino_complementar) VALUES
(1, '2023-12-01', 1, 1, 'gov_empresa', NULL, NULL, 'dev.tributario@e-governe.com', '[1]', NULL, 'gov_empresa', '1', 1, NULL),
(2, '2023-12-01', 1, 1, 'gov_empresa', NULL, NULL, 'dev.tributario@e-governe.com', '[2]', NULL, 'gov_empresa', '2', 1, NULL),
(3, '2023-12-01', 1, 1, 'gov_empresa', NULL, NULL, 'dev.tributario@e-governe.com', '[3]', NULL, 'gov_empresa', '3', 1, NULL),
(4, '2024-01-09', 4, 2, 'gov_socio', NULL, 'vw_trib_socio', 'dev.tributario@e-governe.com', '[8]', NULL, 'gov_empresa', '8', 1, NULL),
(5, '2024-01-09', 4, 2, 'gov_socio', NULL, 'vw_trib_socio', 'dev.tributario@e-governe.com', '[9]', NULL, 'gov_empresa', '9', 2, NULL),
(6, '2024-01-09', 4, 2, 'gov_socio', NULL, 'vw_trib_socio', 'dev.tributario@e-governe.com', '[10]', NULL, 'gov_empresa', '10', 3, NULL);

INSERT INTO acao (id, data, ocorrencia_id, acao_tipo_id) VALUES
(1, '2023-12-05 15:30:00.000', 6, 1);

INSERT INTO ocorrencia_tipo_modelo (id, ocorrencia_tipo_id, assunto, corpo) VALUES
(1, 1, 'Notificação para o CNPJ @CNPJ@', '<!DOCTYPE html><head><title>Notificação</title></head><body><h1>Você está sendo notificado!</h1><p>CNPJ: @CNPJ@</p></body></html>');