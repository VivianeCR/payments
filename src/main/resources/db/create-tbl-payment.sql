create table payment
(
    id_payment        int auto_increment primary key,
    cpf_cnpj          varchar(11)    null,
    debito_code       int(18)    null,
    number_card      varchar(16)    null,
    payment_method varchar(15)    not null,
    amount          decimal(10, 2) not null,
    processing_status varchar(10)    not null
);