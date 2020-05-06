
cat file.txt| awk '{for(i=1;i<=NF;i= i + 1)
if (data[i] != "")
    data[i] = data[i] " " $i
else
    data[i] = $i
} END {for(str in data)
    print data[str]
}'

#拼接大法