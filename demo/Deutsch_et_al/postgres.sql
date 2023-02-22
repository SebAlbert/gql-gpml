CREATE TABLE nodes (
       id TEXT PRIMARY KEY,
       labels TEXT[] NOT NULL DEFAULT '{}',
       properties JSONB NOT NULL DEFAULT '{}'
);

COPY nodes (id, labels, properties) FROM STDIN;
a1	{Account}	{"owner": "Scott", "isBlocked": false}
a2	{Account}	{"owner": "Aretha", "isBlocked": false}
a3	{Account}	{"owner": "Mike", "isBlocked": false}
a4	{Account}	{"owner": "Jay", "isBlocked": true}
a5	{Account}	{"owner": "Charles", "isBlocked": false}
a6	{Account}	{"owner": "Dave", "isBlocked": false}
c1	{Country}	{"name": "Zembla"}
c2	{City,Country}	{"name": "Ankh-Morpork"}
ip1	{IP}	{"number": "123.111", "isBlocked": false}
ip2	{IP}	{"number": "123.222", "isBlocked": false}
p1	{Phone}	{"number": "111", "isBlocked": false}
p2	{Phone}	{"number": "222", "isBlocked": false}
p3	{Phone}	{"number": "333", "isBlocked": false}
p4	{Phone}	{"number": "444", "isBlocked": false}
\.

CREATE TABLE edges (
       id TEXT PRIMARY KEY,
       source TEXT NOT NULL,
       destination TEXT NOT NULL,
       directed BOOLEAN NOT NULL,
       labels TEXT[] NOT NULL DEFAULT '{}',
       properties JSONB NOT NULL DEFAULT '{}',
       FOREIGN KEY (source) REFERENCES nodes(id),
       FOREIGN KEY (destination) REFERENCES nodes(id)
);

COPY edges (id, source, destination, directed, labels, properties) FROM STDIN;
t1	a1	a3	t	{Transfer}	{"date": "2020-01-01", "amount": 8000000}
t2	a3	a2	t	{Transfer}	{"date": "2020-01-02", "amount": 10000000}
t3	a2	a4	t	{Transfer}	{"date": "2020-01-03", "amount": 10000000}
t4	a4	a6	t	{Transfer}	{"date": "2020-01-04", "amount": 10000000}
t5	a6	a3	t	{Transfer}	{"date": "2020-01-06", "amount": 10000000}
t6	a6	a5	t	{Transfer}	{"date": "2020-01-07", "amount": 4000000}
t7	a3	a5	t	{Transfer}	{"date": "2020-01-08", "amount": 6000000}
t8	a5	a1	t	{Transfer}	{"date": "2020-01-09", "amount": 9000000}
li6	a6	c2	t	{isLocatedIn}	{}
li4	a4	c2	t	{isLocatedIn}	{}
li2	a2	c2	t	{isLocatedIn}	{}
li1	a1	c1	t	{isLocatedIn}	{}
li3	a3	c1	t	{isLocatedIn}	{}
li5	a5	c1	t	{isLocatedIn}	{}
sip1	a1	ip1	f	{signInWithIP}	{}
sip2	a5	ip2	f	{signInWithIP}	{}
hp1	a1	p1	f	{hasPhone}	{}
hp5	a5	p1	f	{hasPhone}	{}
hp3	a3	p2	f	{hasPhone}	{}
hp2	a2	p2	f	{hasPhone}	{}
hp4	a4	p3	f	{hasPhone}	{}
hp6	a6	p4	f	{hasPhone}	{}
\.
