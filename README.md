# Spring Reactive
Example on Spring Reactive with Flux and using a for loop to get the information.
This is just a sample for POC.

## How to use?
### POST REQUEST to create CG

**Method Type:** POST

**URI:** http://localhost:8080/reactive/createCG

**Headers:**

_Content-Type: application/json_

_Accept: application/stream+json_

**Request Body:**

`[ {
  "srcDcName" : "DataCenter-0",
  "srcVmName" : "VM-0",
  "journalSize" : 10
}, {
  "srcDcName" : "DataCenter-1",
  "srcVmName" : "VM-1",
  "journalSize" : 11
}, {
  "srcDcName" : "DataCenter-2",
  "srcVmName" : "VM-2",
  "journalSize" : 12
}, {
  "srcDcName" : "DataCenter-3",
  "srcVmName" : "VM-3",
  "journalSize" : 13
}, {
  "srcDcName" : "DataCenter-4",
  "srcVmName" : "VM-4",
  "journalSize" : 14
} ]`

**NOTE: You can test in POSTMAN rest client but you will not get events.**

### GET REQUEST to get all created CGs

**Method Type:** GET

**URI:** http://localhost:8080/reactive/getAllCG/10

Note: Here 10 is the number to simulate the number of CGs, you can give any number greater than 0.

**Headers:**

_Accept: application/stream+json_