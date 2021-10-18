package org.example.model.service.impl;

final class SqlQueries {

    private SqlQueries() {}

    /* ---------------------- User Achievements Section ---------------------- */

    static final String US_ACH_INSERT = "insert into users_achievements (user_id, ach_id) values (?, ?)";
    static final String ACH_INSERT_IMAGE_BY_ID = "update achievements set picture = ? where ach_id = ?";

    static final String LOCALE_ACH_FIND_UNCLAIMED = """
            select a.ach_id as ach_id,
                   la.name_|-| as name,
                   la.flavor_|-| as flavor,
                   a.picture,
                   a.status
            from achievements a
                    inner join localized_ach la on a.ach_id = la.loc_ach_id
            except
            select ua.ach_id as ach_id,
                   la.name_|-| as name,
                   la.flavor_|-| as flavor,
                   a.picture,
                   a.status
            from users_achievements ua
                     inner join achievements a on a.ach_id = ua.ach_id
                     inner join localized_ach la on a.ach_id = la.loc_ach_id
            where ua.user_id = ?""";

    static final String LOCALE_US_ACH_FIND_BY_USER_ID = """
            select ua.ach_id as ach_id,
                   la.name_|-| as name,
                   la.flavor_|-| as flavor,
                   a.picture,
                   a.status
            from users_achievements ua
                inner join achievements a on a.ach_id = ua.ach_id
                inner join localized_ach la on a.ach_id = la.loc_ach_id
            where ua.user_id = ?""";

    /* ---------------------- User Section ---------------------- */

    static final String USER_UPDATE_EXP_AND_LEVEL = "update users set experience = ?, level = ? where user_id = ?";
    static final String USER_SELECT_BY_LOGIN = "select * from users where login = ?";
    static final String USER_SELECT_BY_EMAIL = "select * from users where email = ?";
    static final String USER_SELECT_BY_LEVEL = "select * from users where level = ?";
    static final String USER_COUNT = "select count(user_id) from users";
    static final String USER_COUNT_SOLVED_BY_ID = "select count(task_id) from solved_tasks where user_id = ?";
    static final String USER_DELETE_BY_ID = "update users set status = ? where user_id = ?";
    static final String USER_INSERT_IMAGE_BY_ID = "update users set picture = ? where user_id = ?";
    static final String USER_DELETE_IMAGE_BY_ID = "update users set picture = null where user_id = ?";
    static final String USER_INSERT = "insert into users (login, password, email) values (?, ?, ?)";

    static final String LOCALE_FIND_LEVEL_BY_USER_ID = """
            select u.level,
                   l.exp_threshold,
                   ll.name_|-|
            from users u
            inner join levels l on l.level = u.level
            inner join localized_levels ll on l.level = ll.level
            where user_id = ?""";

    static final String LOCALE_USER_FIND_ALL = """
            select u.user_id as user_id,
                   u.login as login,
                   u.password as password,
                   u.email as email,
                   u.experience as experience,
                   ll.name_|-| as level,
                   u.status as status,
                   u.created as created,
                   u.changed as changed,
                   u.picture as picture
            from users u
            inner join levels l on l.level = u.level
            inner join localized_levels ll on l.level = ll.level""";

    static final String LOCALE_USER_FIND_BY_ID = """
            select u.user_id as user_id,
                   u.login as login,
                   u.password as password,
                   u.email as email,
                   u.experience as experience,
                   ll.name_|-| as level,
                   u.status as status,
                   u.created as created,
                   u.changed as changed,
                   u.picture as picture
            from users u
            inner join levels l on l.level = u.level
            inner join localized_levels ll on l.level = ll.level
            where u.user_id = ?""";

    /* ---------------------- Achievements Section ---------------------- */

    static final String ACH_DELETE_BY_ID = "update achievements set status = ? where task_id = ?";
    static final String ACH_COUNT = "select count(ach_id) from achievements";

    static final String LOCALE_ACH_FIND_BY_ID = """
            select a.ach_id as ach_id,
                   la.name_|-| as name,
                   la.flavor_|-| as flavor,
                   a.picture,
                   a.status
            from achievements a
            inner join localized_ach la on a.ach_id = la.loc_ach_id
            where a.ach_id = ?""";

    static final String LOCALE_ACH_FIND_ALL = """
            select a.ach_id as ach_id,
                   la.name_|-| as name,
                   la.flavor_|-| as flavor,
                   a.picture,
                   a.status
            from achievements a
            inner join localized_ach la on a.ach_id = la.loc_ach_id""";

    static final String LOCALE_ACH_SAVE = """
            do $$
            declare
                nextAchId bigint := nextval('achievements_ach_id_seq');
            begin
                insert into achievements (ach_id, picture)
                values (nextAchId, ?);
                insert into localized_ach (loc_ach_id, name_|-|, flavor_|-|)
                values (nextAchId, ?, ?);
                commit;
            end $$""";

    static final String LOCALE_ACH_UPDATE = """
            do $$
            declare
                achId bigint := ?;
            begin
                update achievements set picture = ?
                where ach_id = achId;
                update localized_ach set name_|-| = ?, flavor_|-| = ?
                where loc_ach_id = achId;
                commit;
            end $$""";

    /* ---------------------- Task Section ---------------------- */

    static final String TASK_DELETE_BY_ID = "update tasks set status = ? where task_id = ?";
    static final String TASK_COUNT = "select count(task_id) from tasks";

    static final String LOCALE_TASK_FIND_ALL_CATEGORIES = """
            select category, name_|-|, flavor_|-|
            from categories""";

    static final String LOCALE_TASK_FIND_BY_ID = """
            select t.task_id as task_id,
                   lt.name_|-| as name,
                   lt.desc_|-| as description,
                   lt.answer_|-| as answer,
                   d.name_|-| as difficulty,
                   c.name_|-| as category,
                   t.experience as experience,
                   t.status as status,
                   t.created as created,
                   t.changed as changed
            from tasks t
            inner join localized_tasks lt on t.task_id = lt.loc_task_id
            inner join difficulties d on d.difficulty = t.difficulty
            inner join categories c on c.category = t.category
            where task_id = ?""";

    static final String LOCALE_TASK_FIND_ALL = """
            select t.task_id as task_id,
                   lt.name_|-| as name,
                   lt.desc_|-| as description,
                   lt.answer_|-| as answer,
                   d.name_|-| as difficulty,
                   c.name_|-| as category,
                   t.experience as experience,
                   t.status as status,
                   t.created as created,
                   t.changed as changed
            from tasks t
                     inner join localized_tasks lt on t.task_id = lt.loc_task_id
                     inner join difficulties d on d.difficulty = t.difficulty
                     inner join categories c on c.category = t.category""";

    static final String LOCALE_TASK_SAVE = """
            do $$
            declare
                nextTaskId bigint := nextval('tasks_task_id_seq');
            begin
                insert into tasks (task_id, experience, difficulty, category)
                values (nextTaskId, ?, ?, ?);
                insert into localized_tasks (loc_task_id, name_|-|, desc_|-|, answer_|-|)
                values (nextTaskId, ?, ?, ?);
                commit;
            end $$""";

    static final String LOCALE_TASK_UPDATE = """
            do $$
            declare
                taskId bigint := ?;
            begin
                update tasks set experience = ?, difficulty = ?, category = ?
                where task_id = taskId;
                update localized_tasks set name_|-| = ?, desc_|-| = ?, answer_|-| = ?
                where loc_task_id = taskId;
                commit;
            end $$""";

    static final String LOCALE_TASK_COUNT_BY_CATEGORIES = """
            select c.name_|-|, count(*)
            from tasks
            inner join categories c on c.category = tasks.category
            group by c.name_|-|""";

    static final String LOCALE_TASK_SELECT_BY_NAME = """
            select t.task_id as task_id,
                   lt.name_|-| as name,
                   lt.desc_|-| as description,
                   lt.answer_|-| as answer,
                   d.name_|-| as difficulty,
                   c.name_|-| as category,
                   t.experience as experience,
                   t.status as status,
                   t.created as created,
                   t.changed as changed
            from localized_tasks lt
                inner join tasks t on t.task_id = lt.loc_task_id
                inner join difficulties d on d.difficulty = t.difficulty
                inner join categories c on c.category = t.category
            where lt.name_|-| = ?""";

    static final String LOCALE_FIND_BY_CATEGORY = """
            select t.task_id as task_id,
                   lt.name_|-| as name,
                   lt.desc_|-| as description,
                   lt.answer_|-| as answer,
                   d.name_|-| as difficulty,
                   c.name_|-| as category,
                   t.experience as experience,
                   t.status as status,
                   t.created as created,
                   t.changed as changed
            from tasks t
                inner join localized_tasks lt on t.task_id = lt.loc_task_id
                inner join difficulties d on d.difficulty = t.difficulty
                inner join categories c on c.category = t.category
            where t.category = ?""";

    /* ---------------------- Solved Task Section ---------------------- */

    static final String SOLVED_INSERT = "insert into solved_tasks (user_id, task_id) values (?, ?)";
    static final String SOLVED_SELECT_BY_USER_ID_AND_TASK_ID = "select * from solved_tasks where user_id = ? and task_id = ?";

    static final String LOCALE_TASK_COUNT_SOLVED_BY_CATEGORIES_FOR_USER = """
            select c.name_|-|, count(*)
            from solved_tasks st
            inner join tasks t on t.task_id = st.task_id
            inner join categories c on c.category = t.category
            where st.user_id = ?
            group by c.name_|-|""";

    static final String LOCALE_SOLVED_SELECT_ALL_BY_USER_ID = """
            select t.task_id as task_id,
                   lt.name_|-| as name,
                   lt.desc_|-| as description,
                   lt.answer_|-| as answer,
                   d.name_|-| as difficulty,
                   c.name_|-| as category,
                   t.experience as experience,
                   t.status as status,
                   t.created as created,
                   t.changed as changed
            from solved_tasks st
                inner join tasks t on t.task_id = st.task_id
                inner join localized_tasks lt on t.task_id = lt.loc_task_id
                inner join difficulties d on d.difficulty = t.difficulty
                inner join categories c on c.category = t.category
            where st.user_id = ?""";
}
