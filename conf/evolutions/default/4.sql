# Schema

# --- !Ups

INSERT INTO customer (name,email,first_name,last_name,company_name,street,zip,city,country,phone) 
values ('Dui Corporation','sociis@aenimSuspendisse.com','Leonard','Walsh','Diam Dictum Consulting','P.O. Box 132, 6902 Donec Av.','31808','Pellezzano','Central African Republic','1-651-190-0528')
,('Nullam Foundation','dictum.Phasellus@lorem.org','Baker','Morris','Amet Ornare Lectus Institute','833-6739 Donec Avenue','13340','Lakewood','Iran','1-775-943-3848')
,('Primis In Corporation','ante@nibhQuisquenonummy.ca','Rhiannon','Gentry','Nullam LLP','P.O. Box 755, 5777 Eu Ave','0427KY','Nelson','Italy','956-7104'),
('Condimentum Eget Volutpat Foundation','ut.lacus.Nulla@porttitorerosnec.edu','Blaine','Noble','Diam Limited','P.O. Box 356, 5610 Nec Rd.','3274','Sneek','Niue','411-8702'),
('Sed Industries','semper.pretium.neque@Nullasempertellus.org','Charles','Petersen','Ipsum Consulting','Ap #716-6283 Mauris, St.','2403','Ribnitz-Damgarten','Guam','711-2402'),
('Elit Industries','est@ultricessit.org','Abel','Baldwin','Ultrices Institute','6406 Pretium Ave','35066','Aulnay-sous-Bois','Switzerland','1-641-369-3802'),
('Nascetur Company','cursus@augueac.co.uk','Calista','Fleming','Lacus Consulting','5771 Enim. Ave','763099','Hartford','Micronesia','166-8448'),
('Pellentesque Sed Company','posuere@ligulaDonec.net','Justin','Merritt','Enim Incorporated','6514 Donec Avenue','3803','Mont-Saint-Guibert','Latvia','830-8220'),
('Gravida Incorporated','vitae.nibh@urna.co.uk','Hope','Douglas','Eu Company','P.O. Box 122, 7222 Ornare, St.','957644','Teltow','Morocco','1-715-230-3979'),
('Maecenas Mi Felis LLC','egestas@magna.net','Anika','Bird','Tellus Sem Foundation','Ap #371-1406 Sapien Ave','3455','Gierle','Belarus','1-512-237-2732');


# --- !Downs

delete from customer;