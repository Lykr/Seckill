import os

file = open(os.path.split(os.path.realpath(__file__))[0] + '/create_user.sql', 'w')

prefix = "INSERT INTO `user` VALUES "
file.write(prefix)

phone = 15000000000

for i in range(10000):
    if i != 9999: content = "('" + str(phone) + "', 'user', 'userpwd', null, '2019-01-09 17:08:16'), "
    else: content = "('" + str(phone) + "', 'user', 'userp', null, '2019-01-09 17:08:16');"
    file.write(content)
    phone += 1

file.close()