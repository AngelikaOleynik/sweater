-- расширение для postgres
create extension if not exists pgcrypto;

-- обновление паролей
update usr set password = crypt(password, gen_salt('bf', 8));