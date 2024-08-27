-- Populando a tabela organizacao
INSERT INTO public.organizacao (id, nome) VALUES
(nextval('organizacao_id_seq'), 'Organização Alpha'),
(nextval('organizacao_id_seq'), 'Organização Beta'),
(nextval('organizacao_id_seq'), 'Organização Gamma'),
(nextval('organizacao_id_seq'), 'Organização Delta'),
(nextval('organizacao_id_seq'), 'Organização Epsilon');

-- Populando a tabela usuario com UUIDs para o campo keycloak_id
INSERT INTO public.usuario (id, nome, email, keycloak_id) VALUES
(nextval('usuario_id_seq'), 'Alice Silva', 'alice.silva@example.com', '550e8400-e29b-41d4-a716-446655440000'),
(nextval('usuario_id_seq'), 'Bruno Santos', 'bruno.santos@example.com', '550e8400-e29b-41d4-a716-446655440001'),
(nextval('usuario_id_seq'), 'Carla Pereira', 'carla.pereira@example.com', '550e8400-e29b-41d4-a716-446655440002'),
(nextval('usuario_id_seq'), 'Daniel Costa', 'daniel.costa@example.com', '550e8400-e29b-41d4-a716-446655440003'),
(nextval('usuario_id_seq'), 'Elena Almeida', 'elena.almeida@example.com', '550e8400-e29b-41d4-a716-446655440004');

-- Populando a tabela projeto
INSERT INTO public.projeto (id, nome, organizacao_id) VALUES
(nextval('projeto_id_seq'), 'Projeto Apollo', 1),
(nextval('projeto_id_seq'), 'Projeto Hermes', 2),
(nextval('projeto_id_seq'), 'Projeto Athena', 3),
(nextval('projeto_id_seq'), 'Projeto Zeus', 4),
(nextval('projeto_id_seq'), 'Projeto Artemis', 5);

-- Populando a tabela tarefa
INSERT INTO public.tarefa (id, titulo, descricao, so, screenshots, caminho, data_correcao, data_criacao, prioridade, classificacao, status, fechada, posicao, projeto_id, feedback) VALUES
(nextval('tarefa_id_seq'), 'Corrigir bug na tela de login', 'Corrigir o erro de autenticação na tela de login', 'Windows', 'screenshot1.png', '/caminho/correcoes', '2024-08-22', '2024-08-21', 1, 1, 1, true, 1, 1, 'feedback'),
(nextval('tarefa_id_seq'), 'Implementar nova funcionalidade', 'Adicionar a funcionalidade de exportação de relatórios', 'Linux', NULL, NULL, NULL, '2024-08-20', 2, 2, 2, false, 1, 2, 'feedback'),
(nextval('tarefa_id_seq'), 'Melhorar desempenho do sistema', 'Otimizar a consulta ao banco de dados', 'MacOS', 'screenshot2.png', '/caminho/melhorias', '2024-08-23', '2024-08-21', 1, 1, 1, true, 1, 3, 'feedback'),
(nextval('tarefa_id_seq'), 'Atualizar documentação', 'Atualizar a documentação do sistema para a versão mais recente', NULL, NULL, '/caminho/documentacao', NULL, '2024-08-21', 3, 2, 2, true, 1, 4, 'feedback'),
(nextval('tarefa_id_seq'), 'Corrigir erro na exportação de dados', 'Corrigir o erro ao exportar dados no formato CSV', 'Windows', 'screenshot3.png', '/caminho/correcoes2', '2024-08-25', '2024-08-21', 2, 3, 3, true, 1, 5, 'feedback');

-- Populando a tabela usuario_tarefa
INSERT INTO public.usuario_tarefa (usuario_id, tarefa_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(1, 3),
(2, 4),
(3, 5),
(4, 1),
(5, 2);

-- Populando a tabela usuario_projeto
INSERT INTO public.usuario_projeto (usuario_id, projeto_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(1, 3),
(2, 4),
(3, 5),
(4, 1),
(5, 2);