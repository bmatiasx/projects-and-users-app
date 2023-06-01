insert into users (name, email)
values
    ('Rob', 'robpike@google.com'),
    ('James', 'jgosling@sun.com'),
    ('Brian', 'bgoetz@oracle.com'),
    ('Max', 'mbonfit@google.com'),
    ('Jim', 'jbrown@sun.com'),
    ('Mike', 'msheridan@oracle.com'),
    ('Patrick', 'pnaughton@google.com'),
    ('Arthur', 'avanhoff@sun.com'),
    ('Robert', 'bobmartin@oracle.com'),
    ('Dave', 'dcheney@google.com'),
    ('Ken', 'kthompson@sun.com'),
    ('Ker', 'bkernighan@oracle.com'),
    ('Bill', 'bkennedy@google.com'),
    ('Linus', 'ltorvalds@sun.com'),
    ('Tim', 'tcook@apple.com'),
    ('Steve', 'sjobs@apple.com'),
    ('Woz', 'stevewozniak@yahoo.com'),
    ('Marissa', 'mmayer@oracle.com'),
    ('Rachel', 'rcarlson@google.com'),
    ('Melanie', 'mperkins@gmail.com'),
    ('Susan', 'swocicki@oracle.com'),
    ('Lisa', 'lsu@google.com'),
    ('Meg', 'mwhitman@sun.com'),
    ('Ursula', 'uburns@oracle.com');

insert into projects (name, description)
values
    ('JEP-143', 'Improved contended locking'),
    ('JEP-405', 'Record patterns and array patterns'),
    ('JEP-427', 'Pattern matching for switch'),
    ('JEP-409', 'Sealed classes'),
    ('JEP-395', 'Records'),
    ('JEP-394', 'Pattern matching for instanceof'),
    ('JEP-301', 'Enhanced enums'),
    ('JEP-302', 'Lambda leftovers'),
    ('JEP-323', 'Local-variable syntax for lambda parameters'),
    ('JEP-212', 'Resolving lint and doclint warnings');

insert into project_by_user (project_id, user_id)
values
    (1, 1),
    (1, 2),
    (2, 1),
    (3, 2),
    (4, 10),
    (4, 11),
    (4, 12),
    (6, 10),
    (6, 11),
    (6, 12),
    (5, 21),
    (5, 12),
    (7, 8);