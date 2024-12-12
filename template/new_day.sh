#!/bin/sh
files=`ls ../ | sed -e s/[^0-9]//g`
number=0

for file in $files
do
  echo $file
  tmp=$((file-"0"))
  if [ "$tmp" -gt "$number" ]; then
    number=$tmp
  fi
done

newDay=$((number+1))

rsync -av --exclude='new_day.sh' . "../day$newDay/"
# find . -type f -not -iname 'new_day.sh' -exec cp '{}' "../day$newDay/" ';'

sed -i -e "s/XX/$newDay/g" "../day$newDay/Solution.java"
sed -i -e "s/XX/$newDay/g" "../day$newDay/run.sh"
