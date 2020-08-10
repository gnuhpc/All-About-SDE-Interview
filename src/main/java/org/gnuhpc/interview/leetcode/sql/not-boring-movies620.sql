select * from cinema
where id%2=1 and cinema.description != 'boring'
order by rating desc