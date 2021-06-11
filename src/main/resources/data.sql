INSERT INTO roles values (0,"ROLE_ADMIN");
INSERT INTO roles values (0,"ROLE_ESTANDAR");

INSERT INTO usuarios values (0,"Adivinas","Calle 13",22,"u@email.com","2021/05/20","https://images-na.ssl-images-amazon.com/images/I/610FlSOYxiL._AC_SX522_.jpg","H","Unai Gonzalez","$2y$10$8zHvivHClP.zLVJB0kwBLe4/XmbfR.HVZx2US.fF8X/Uf318cYOEi","123","666","usu1",2);
INSERT INTO usuarios values (0,"Adivinas","Calle 13",22,"r@email.com","2021/05/20","https://statics.proyectoclubes.com/images/sporting/opengraph_image.png?2","H","Roberto Rodriguez","$2y$10$8zHvivHClP.zLVJB0kwBLe4/XmbfR.HVZx2US.fF8X/Uf318cYOEi","123","662","usu2",2);
INSERT INTO usuarios values (0,"Adivinas","Calle 13",22,"x@email.com","2021/05/20","https://estaticos.muyinteresante.es/media/cache/1140x_thumb/uploads/images/gallery/594a1ced5bafe85dfd3c9869/gato-romano_0.jpg","M","Xenia Rodriguez","$2y$10$8zHvivHClP.zLVJB0kwBLe4/XmbfR.HVZx2US.fF8X/Uf318cYOEi","123","661","usu3",2);
INSERT INTO usuarios values (0,"","",0,"admin@email.com","2021/05/20","","H","Administrador","$2a$10$vk1o5JMQqAIF1EV7L2j3kePhOOlRI84FK51XhyBRt5ITv85UIuOde","admin","","admin",1);

INSERT INTO amigos values (0,1,1,2);
INSERT INTO amigos values (0,1,1,3);
INSERT INTO amigos values (0,1,2,3);

INSERT INTO mensajes values (0,"Prueba","2021/05/20 12:50:30",1,2);
INSERT INTO mensajes values (0,"Prueba 2","2021/05/20 12:51:24",2,1);
INSERT INTO mensajes values (0,"Prueba 3","2021/05/20 12:52:54",1,2);
INSERT INTO mensajes values (0,"Prueba 4","2021/05/20 12:53:15",1,2);
INSERT INTO mensajes values (0,"Prueba 5","2021/05/20 12:54:34",2,1);

INSERT INTO entradas values (0,"2021/05/20 12:10:34","/images","Entrada1",1);
INSERT INTO entradas values (0,"2021/05/20 12:22:34","/images","Entrada2",1);
INSERT INTO entradas values (0,"2021/05/20 12:30:34","/images","Entrada3",1);
INSERT INTO entradas values (0,"2021/05/20 12:44:34","/images","Entrada1",2);
INSERT INTO entradas values (0,"2021/05/20 12:58:34","/images","Entrada1",3);

INSERT INTO megustas values (0,1,1);
INSERT INTO megustas values (0,2,1);
INSERT INTO megustas values (0,3,1);

INSERT INTO comentarios values (0,"Buenaaas","2021/05/20 12:54:34",null,1,1);
INSERT INTO comentarios values (0,"QUe tal tio","2021/05/20 12:56:34",1,1,2);
INSERT INTO comentarios values (0,"Eyy","2021/05/20 12:57:34",1,1,3);