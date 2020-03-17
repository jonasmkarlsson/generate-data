**Generates a dataset, one or more columns and rows, of different pre-defined column(s) or sequence(s).**

# Build
Build with Apache Maven: `mvn clean install`. Executable file are `target/generate-data.jar`. Copy file to your chosen destination or run it directly from this location.

# Types of columns
There exists two major types of columns: Simple and Sequence.

## Simple
A simple column reads data from a text file that contains a collection of appropriated values. A random value are then fetched from that collection and added to the generated dataset. Please note that today, only Swedish values are supported.

### List if simple columns   
- Company - Companies of Sweden.
- Firstname - First names of Sweden.
- Lastname - Last names of Sweden.
- Location - Location/Cities of Sweden.
- Street - Streets of Sweden.
- Avatar URL - A link to a generated avatar (http://avatars.adorable.io) with the size 100x100 pixels

## Sequence
The sequence column are using regular expression to generate the data. 
The first rule are that a sequence column must be inside of the char "'", ex: 'sequence(\\+46-\\([0-3]{2}\\)-[0-9]{7})'.

**NB!** When using the sequence don't forget to use escape sequence on special characters that are used in regression expression.

# Usage
> java -jar generate-data.jar [options]

Executing without any options prints out the available options.

Option               | Comment
:--------------------| :-------------
-d,--delimiter <arg> | Specify delimiter, only usable when several fields are combined. Default are ','.
-f,--field <arg>     | Type of column(s) to generate.
-n,--lines <arg>     | Number of rows to generate. Default are '10'.
-v,--version         | Display version information.

**NB** When generating multiple fields/columns, make sure that there is no space between the different column types. Ex. are the syntax `firstname,lastname` correct, `firstname, lastname` syntax are NOT.

# Examples

## Simple columns
Generate default number of rows with person data, in this case with first & last name, street, location, company and avatar URL

> java -jar generate-data.jar -f firstname,lastname,street,location,company,avatarurl

If you want to generate a specific number of rows, add the option -n X, where 'X' is a number. In the below example, 25 rows are created.

> java -jar generate-data.jar -f firstname,lastname,street,location,company,avatarurl -n 25

## Sequence column
Using the sequencer column to create a sequence of standardized data, accordingly to a regression expression.

Simulate first name starting wit A-Z and then follows with 7 characters if a-z

> java -jar generate-data.jar -f 'sequence([A-Z][a-z]{7})'

Simulate a phone number with country code, prefix and number (Swedish).

> java -jar generate-data.jar -f 'sequence(\\+46-\\([0-3]{2}\\)-[0-9]{7})'

## Combine simple and sequence column
Take the first example from the simple column and add a sequence column.

> java -jar generate-data.jar -f firstname,lastname,street,location,company,'sequence(\+46-\([0-3]{2}\)-[0-9]{7})'