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

-- Populando a tabela subclassificacao
INSERT INTO public.subclassificacao (id, nome) VALUES
(nextval('subclassificacao_id_seq'), 'Funcional'),
(nextval('subclassificacao_id_seq'), 'Desempenho'),
(nextval('subclassificacao_id_seq'), 'Segurança'),
(nextval('subclassificacao_id_seq'), 'Integração'),
(nextval('subclassificacao_id_seq'), 'Outro'),
(nextval('subclassificacao_id_seq'), 'Normal'),
(nextval('subclassificacao_id_seq'), 'Emergencial');

-- Populando a tabela classificacao
INSERT INTO public.classificacao (id, nome, subclassificacao_id) VALUES
(nextval('classificacao_id_seq'), 'Incidente', 4),
(nextval('classificacao_id_seq'), 'Problema', 5),
(nextval('classificacao_id_seq'), 'Mudança', 6);

-- Populando a tabela tarefa
INSERT INTO public.tarefa (id, titulo, descricao, so, screenshots, caminho, data_fechamento, data_criacao, data_estimada, prioridade, status, fechada, posicao, projeto_id, feedback, classificacao_id) VALUES
(nextval('tarefa_id_seq'), 'Corrigir bug na tela de login', 'Corrigir o erro de autenticação na tela de login', 'Windows', ARRAY[decode('cGluZGVyCg==', 'base64')], '/caminho/correcoes', '2024-08-22', '2024-08-21', '2024-08-21', 1, 1, true, 1, 1, 'feedback', 1),
(nextval('tarefa_id_seq'), 'Implementar nova funcionalidade', 'Adicionar a funcionalidade de exportação de relatórios', 'Linux', NULL, NULL, NULL, '2024-08-20', '2024-08-21', 2, 2, false, 1, 2, 'feedback', 2),
(nextval('tarefa_id_seq'), 'Melhorar desempenho do sistema', 'Otimizar a consulta ao banco de dados', 'MacOS', ARRAY[decode('cGluZGVyCg==', 'base64')], '/caminho/melhorias', '2024-08-23', '2024-08-21', '2024-08-21', 1, 1, true, 1, 3, 'feedback', 3),
(nextval('tarefa_id_seq'), 'Atualizar documentação', 'Atualizar a documentação do sistema para a versão mais recente', NULL, NULL, '/caminho/documentacao', NULL, '2024-08-21', '2024-08-21', 3, 2, true, 1, 4, 'feedback', 2),
(nextval('tarefa_id_seq'), 'Corrigir erro na exportação de dados', 'Corrigir o erro ao exportar dados no formato CSV', 'Windows', ARRAY[decode('cGluZGVyCg==', 'base64')], '/caminho/correcoes2', '2024-08-25', '2024-08-21', '2024-08-21', 2, 3, true, 1, 5, 'feedback', 1);

INSERT INTO public.comentario (id, conteudo, tarefa_id, data_criacao, usuario_id) VALUES
(nextval('comentario_id_seq'), 'Comentario 1', 1, NOW(), 1),
(nextval('comentario_id_seq'), 'Comentario 2', 2, NOW(), 2),
(nextval('comentario_id_seq'), 'Comentario 3', 3, NOW(), 3),
(nextval('comentario_id_seq'), 'Comentario 4', 4, NOW(), 4),
(nextval('comentario_id_seq'), 'Comentario 5', 5, NOW(), 5);

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