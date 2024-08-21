-- Populando a tabela organizacao
INSERT INTO public.organizacao (nome) VALUES
('Organização Alpha'),
('Organização Beta'),
('Organização Gamma'),
('Organização Delta'),
('Organização Epsilon');

-- Populando a tabela usuario
INSERT INTO public.usuario (nome, email, keycloak_id) VALUES
('Alice Silva', 'alice.silva@example.com', 'keycloak-id-1'),
('Bruno Santos', 'bruno.santos@example.com', 'keycloak-id-2'),
('Carla Pereira', 'carla.pereira@example.com', 'keycloak-id-3'),
('Daniel Costa', 'daniel.costa@example.com', 'keycloak-id-4'),
('Elena Almeida', 'elena.almeida@example.com', 'keycloak-id-5');

-- Populando a tabela projeto
INSERT INTO public.projeto (nome, organizacao_id) VALUES
('Projeto Apollo', 1),
('Projeto Hermes', 2),
('Projeto Athena', 3),
('Projeto Zeus', 4),
('Projeto Artemis', 5);

-- Populando a tabela tarefa
INSERT INTO public.tarefa (titulo, descricao, so, screenshots, caminho, data_correcao, data_criacao, prioridade, classificacao, status, projeto_id) VALUES
('Corrigir bug na tela de login', 'Corrigir o erro de autenticação na tela de login', 'Windows', 'screenshot1.png', '/caminho/correcoes', '2024-08-22', '2024-08-21', 1, 1, 1, 1),
('Implementar nova funcionalidade', 'Adicionar a funcionalidade de exportação de relatórios', 'Linux', NULL, NULL, NULL, '2024-08-20', 2, 2, 2, 2),
('Melhorar desempenho do sistema', 'Otimizar a consulta ao banco de dados', 'MacOS', 'screenshot2.png', '/caminho/melhorias', '2024-08-23', '2024-08-21', 1, 1, 1, 3),
('Atualizar documentação', 'Atualizar a documentação do sistema para a versão mais recente', NULL, NULL, '/caminho/documentacao', NULL, '2024-08-21', 3, 2, 2, 4),
('Corrigir erro na exportação de dados', 'Corrigir o erro ao exportar dados no formato CSV', 'Windows', 'screenshot3.png', '/caminho/correcoes2', '2024-08-25', '2024-08-21', 2, 3, 3, 5);

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
