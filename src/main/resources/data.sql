CREATE MATERIALIZED VIEW public.tasks_per_project AS
SELECT t.projet_id, COUNT(t.task_id) AS tasks_count
FROM task t
GROUP BY t.projet_id;