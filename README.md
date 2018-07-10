## Purpose

```secret-spawn``` is a tool that produces passwords from real phrases that are easy to memorize.  
It can produce long passwords or "hackish" passwords. It uses an external source, like a random Wikipedia  
article, to pick a random real phrase.

## History

Its 2017 and security experts just realized that complex passwords do not offer that much security,  
admitting that they have more impact on usability than security. You now wonder what you should do to pick  
a secure password. Experts now suggest to think the length, not complexity of your password.  
Just like Mark Burnett, the author of ```Perfect Passwords```:

```
"A longer password is usually better than a more random password,

as long as the password is at least 12-15 characters long."
```

Recent studies show that a password of four words can be harder to crack than a short random password.

Even the guy who, back in 2003, wrote the ```Password Rulebook``` (officially named   ```NIST Special Publication 800-63 Digital Identity Guidelines Appendix A```),
takes it all back. The then-NIST manager Bill Burr wrote an 8-page set of instructions that now he is sorry about.
The rulebook was the go-to password-setting guide for federal agencies, universities and large companies.

### Sources:

- Wired  
  https://www.wired.com/2016/05/password-tips-experts/
  
- Wall Street Journal  
  https://www.wsj.com/articles/the-man-who-wrote-those-password-rules-has-a-new-tip-n3v-r-m1-d-1502124118

## Build

```
./gradlew clean build
```

## Run

```
java -jar build/libs/secretspawn.jar
```

## Options

- -length  
  Set the password's length

- -source [wikipedia]  
  The source the phrase will be fetched from.

- -format [simple, hackish]  
  The secret's format
