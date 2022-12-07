# ecommerce-example
This project is a example and model of Ecommerce.
There is a SpringBoot, JSP, Hibernate, JavaScript and others tools.
<br>
<br>
Please, necessary update de files above.
<br>
<br>
<br>
<br>
dpaulla-ecommerce project-><br>
application.properties <br>
  app.config.pagseguro.email=yourEmail <br>
  app.config.pagseguro.token=yourToken <br>
  <br>
  <br>
dpaulla-mail-server -> <br>
application.properties <br>
  app.mail.contact.from=yourEmail <br>
  app.mail.contact.passwd=yourPasswordEmail <br>
  app.mail.contact.smtp=yourSMTP <br>
  app.mail.contact.port=yourSMTPPOrt <br>
  app.config.pagseguro.token=yourToken <br>
<br>
<br>
<br>
<br>
After JPA Database creation, its necessary: <br>
drop table user_roles; create table user_roles (user_user_id bigint(20), roles_role_id bigint(20)); <br>
insert into user_roles values(1,1); <br>
insert into user_roles values(2,2); <br>
insert into user_roles values(3,3); <br>
