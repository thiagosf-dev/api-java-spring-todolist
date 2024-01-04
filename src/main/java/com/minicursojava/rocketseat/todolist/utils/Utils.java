package com.minicursojava.rocketseat.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
  private Utils() {
    throw new IllegalStateException("Utility class");
  }

  public static void copyNonNullProperties(Object source, Object target) {
    BeanUtils.copyProperties(source, target, getNullPropertyName(source));
  }

  public static String[] getNullPropertyName(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);

    PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();

    for (PropertyDescriptor pd : pds) {
      var srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) {
        emptyNames.add(pd.getName());
      }
    }

    var result = new String[emptyNames.size()];

    return emptyNames.toArray(result);
  }

}
