/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.accumulo.core.manager.thrift;


public enum ManagerState implements org.apache.thrift.TEnum {
  INITIAL(0),
  HAVE_LOCK(1),
  SAFE_MODE(2),
  NORMAL(3),
  UNLOAD_METADATA_TABLETS(4),
  UNLOAD_ROOT_TABLET(5),
  STOP(6);

  private final int value;

  private ManagerState(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static ManagerState findByValue(int value) { 
    switch (value) {
      case 0:
        return INITIAL;
      case 1:
        return HAVE_LOCK;
      case 2:
        return SAFE_MODE;
      case 3:
        return NORMAL;
      case 4:
        return UNLOAD_METADATA_TABLETS;
      case 5:
        return UNLOAD_ROOT_TABLET;
      case 6:
        return STOP;
      default:
        return null;
    }
  }
}