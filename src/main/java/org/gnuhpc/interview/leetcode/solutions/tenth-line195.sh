row_num=$(cat file.txt | wc -l)
if [ $row_num -ge 10 ];then
    awk '{if(NR==10){print $0}}' file.txt
fi