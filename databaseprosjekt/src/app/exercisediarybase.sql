create table user (
	uid integer not null,
    primary key (uid)
);

create table workout (
	wid integer not null,
    date date,
    time time(0),
    note text,
    duration integer unsigned,
    fitness tinyint check (fitness between 1 and 10),
    performance tinyint check (performance between 1 and 10),
    primary key (wid)
);

create table userworkedout (
	uid integer not null,
    wid integer not null,
    primary key (uid, wid),
    foreign key (uid) references user(uid)
		on update cascade
        on delete cascade,
    foreign key (wid) references workout(wid)
		on update cascade
        on delete cascade
);

create table freeexercise (
	feid integer not null,
    freedescription text,
    primary key (feid)
);

create table machine (
	mid integer not null,
    name varchar(45),
    functiondescription text,
    primary key (mid)
);

create table machineexercise (
	meid integer not null,
    kilos int,
    sets int,
    mid integer not null,
    primary key (meid),
    foreign key (mid) references machine(mid)
		on update cascade
        on delete cascade
);

create table exercisegroup (
	egid integer not null,
    name varchar(45),
    primary key (egid)
);

create table exercise (
	eid integer not null,
    name varchar(45),
    type boolean not null,
    feid int,
    meid int,
    primary key (eid),
    foreign key (feid) references freeexercise(feid)
		on update cascade
        on delete cascade,
    foreign key (meid) references machineexercise(meid)
		on update cascade
        on delete cascade,
	constraint only_one_foreign_key check (feid is not null xor meid is not null)
);

create table exerciseispartofgroup (
	eid integer not null,
    egid integer not null,
    primary key (eid, egid),
    foreign key (eid) references exercise(eid)
		on update cascade
        on delete cascade,
    foreign key (egid) references exercisegroup(egid)
		on update cascade
        on delete cascade
);

create table workoutcontains (
	wid integer not null,
    eid integer not null,
    primary key (wid, eid),
    foreign key (wid) references workout(wid)
		on update cascade
        on delete cascade,
    foreign key (eid) references exercise(eid)
		on update cascade
        on delete cascade
);