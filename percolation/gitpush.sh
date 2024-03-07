#!/bin/bash

if [ "$#" -lt 2 ]; then
    echo "Usage: $0 <filename> <commit_message>"
    exit 1
fi

commit_message="${!#}"

for ((i=1; i<$#; i++)); do
    git add "${!i}"
done

git commit -m"$commit_message"
git push

echo "Git operations completed successfully"