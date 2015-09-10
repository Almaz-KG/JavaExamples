
create table PEOPLE
(
	ID int(11) NOT NULL auto_increment,
	NAME varchar(100),
	PASSWORD varchar(500),

	primary key (ID),
	CONSTRAINT personName UNIQUE (NAME)
)


